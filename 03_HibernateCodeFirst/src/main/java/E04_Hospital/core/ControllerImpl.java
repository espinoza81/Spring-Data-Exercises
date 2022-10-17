package E04_Hospital.core;

import E04_Hospital.common.AddMessages;
import E04_Hospital.common.OutputMessages;
import E04_Hospital.core.interfaces.Controller;
import E04_Hospital.entities.Diagnose;
import E04_Hospital.entities.Medicament;
import E04_Hospital.entities.Patient;
import E04_Hospital.entities.Visitation;
import E04_Hospital.io.ConsoleReader;
import E04_Hospital.io.ConsoleWriter;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

public class ControllerImpl implements Controller {
    private final ConsoleReader reader;
    private final ConsoleWriter writer;
    private final EntityManager entityManager;

    public ControllerImpl(ConsoleReader reader, ConsoleWriter writer, EntityManager entityManager) {
        this.reader = reader;
        this.writer = writer;
        this.entityManager = entityManager;
    }

    @Override
    public String addPatient() throws IOException {

        String firstName = validateName("пациента", "Името");
        String lastName = validateName("пациента", "Фамилията");
        LocalDate birthDate = validateData();

        writer.writeLine(AddMessages.HAS_INSURANCE);
        String hasMedicalInsuranceStr = reader.readLine();
        boolean hasMedicalInsurance = hasMedicalInsuranceStr.toLowerCase(Locale.ROOT).equals("y");

        Patient patient = new Patient(firstName, lastName, birthDate, hasMedicalInsurance);

        entityManager.getTransaction().begin();
        entityManager.persist(patient);
        entityManager.getTransaction().commit();
        return OutputMessages.PATIENT_ADDED;
    }

    @Override
    public String addDiagnose() throws IOException {

        String name = validateName("диагнозата", "Името");

        Diagnose diagnose = new Diagnose(name);

        entityManager.getTransaction().begin();
        entityManager.persist(diagnose);
        entityManager.getTransaction().commit();
        return OutputMessages.DIAGNOSE_ADDED;
    }

    @Override
    public String addMedicament() throws IOException {

        String name = validateName("медикаментът", "Името");

        Medicament medicament = new Medicament(name);

        entityManager.getTransaction().begin();
        entityManager.persist(medicament);
        entityManager.getTransaction().commit();
        return OutputMessages.MEDICAMENT_ADDED;
    }

    @Override
    public String addVisitation() throws IOException {
        Visitation visitation = new Visitation();

        Patient patient = searchPatientById();
        visitation.setPatient(patient);

        Diagnose diagnose = searchDiagnoseById();
        visitation.setDiagnose(diagnose);
        patient.addDiagnose(diagnose);

        writer.writeLine(AddMessages.MEDICAMENT_COUNT);
        int numberMedicament = reader.readInt();

        for (int i = 0; i < numberMedicament; i++) {
            Medicament medicament = searchMedicamentById();
            visitation.addMedicament(medicament);
            patient.addMedicament(medicament);
        }

        patient.addVisitation(visitation);

        entityManager.getTransaction().begin();
        entityManager.persist(visitation);
        entityManager.persist(patient);
        entityManager.getTransaction().commit();

        return OutputMessages.VISITATION_ADDED;
    }

    @Override
    public LocalDate validateData() {
        LocalDate date = null;
        while (true) {
            writer.writeLine(AddMessages.ADD_BIRTHDAY);
            try {
                date = LocalDate.parse(reader.readLine());
                break;
            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                writer.writeLine(e.getMessage());
            }
        }
        return date;
    }

    @Override
    public String validateName(String entity, String field) throws IOException {
        boolean nameError = true;
        String name = null;
        while (nameError) {
            writer.writeLine(String.format(AddMessages.ADD_NAME, entity, field));
            name = reader.readLine();
            if (name.trim().isEmpty()) {
                writer.writeLine(String.format(OutputMessages.NAME_EMPTY, field));
                continue;
            }
            if (name.trim().length() > 50) {
                writer.writeLine(String.format(OutputMessages.NAME_MORE_THAN, field));
                continue;
            }
            nameError = false;
        }

        return name;
    }

    @Override
    public Patient searchPatientById() throws IOException {
        writer.writeLine(AddMessages.FIND_PATIENT);
        int id = reader.readInt();
        return entityManager.find(Patient.class, id);
    }

//    @Override
//    public Class<?> searchById(String msg, Class<?> clazz) throws IOException {
//        writer.writeLine(msg);
//        int id = reader.readInt();
//        return (Class<?>) entityManager.find(clazz, id);
//    }

    @Override
    public Diagnose searchDiagnoseById() throws IOException {
        writer.writeLine(AddMessages.FIND_DIAGNOSE);
        int id = reader.readInt();
        return entityManager.find(Diagnose.class, id);
    }

    @Override
    public Medicament searchMedicamentById() throws IOException {
        writer.writeLine(AddMessages.FIND_MEDICAMENT);
        int id = reader.readInt();
        return entityManager.find(Medicament.class, id);
    }

    @Override
    public String searchVisitationForDate() throws IOException {
        writer.writeLine(AddMessages.FIND_VISITATION_DATE);
        LocalDate date = LocalDate.parse(reader.readLine());

        List<Visitation> visitationsForDate = entityManager.
                createQuery("SELECT v FROM Visitation v WHERE v.date = :date_to_search", Visitation.class).
                setParameter("date_to_search", date).
                getResultList();


        return visitationsForDate.stream().map(Visitation::toString).collect(Collectors.joining(System.lineSeparator()));
    }
}