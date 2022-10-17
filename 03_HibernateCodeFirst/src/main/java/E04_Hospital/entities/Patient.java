package E04_Hospital.entities;

import javax.persistence.*;
import java.sql.Blob;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "_04_patients")
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    private String address;

    @Column(unique = true)
    private String email;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    private Blob picture;

    @Column(name = "has_medical_insurance", nullable = false)
    private boolean hasMedicalInsurance;

    @OneToMany(mappedBy = "patient")
    private Set<Visitation> visitations;

    @ManyToMany
    @JoinTable(
            name = "_04_patients_diagnoses",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "diagnose_id", referencedColumnName = "id")
    )
    private Set<Diagnose> diagnoses;

    @ManyToMany
    @JoinTable(
            name = "_04_patients_medicaments",
            joinColumns = @JoinColumn(name = "patient_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "medicament_id", referencedColumnName = "id")
    )
    private Set<Medicament> medicaments;

    public Patient() {
    }

    public Patient(String firstName, String lastName, LocalDate birthDate, boolean hasMedicalInsurance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.hasMedicalInsurance = hasMedicalInsurance;
        this.visitations = new HashSet<>();
        this.diagnoses = new HashSet<>();
        this.medicaments = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public boolean isHasMedicalInsurance() {
        return hasMedicalInsurance;
    }

    public void setHasMedicalInsurance(boolean hasMedicalInsurance) {
        this.hasMedicalInsurance = hasMedicalInsurance;
    }

    public Set<Visitation> getVisitations() {
        return Collections.unmodifiableSet(visitations);
    }

    public void addVisitation(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public Set<Diagnose> getDiagnoses() {
        return Collections.unmodifiableSet(diagnoses);
    }

    public void addDiagnose(Diagnose diagnose) {
        this.diagnoses.add(diagnose);
    }

    public Set<Medicament> getMedicaments() {
        return Collections.unmodifiableSet(medicaments);
    }

    public void addMedicament(Medicament medicament){
        this.medicaments.add(medicament);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + System.lineSeparator() +
                "постоянен адрес: " + (address == null ? "не е въведен" : address) + System.lineSeparator() +
                "e-mail: " + (email == null ? "не е въведен" : email) + System.lineSeparator() +
                "роден на: " + birthDate + System.lineSeparator() +
                "осигурен: " + (hasMedicalInsurance ? "да" : "не") + System.lineSeparator() +
                "брой прегледи: " + visitations.size();
    }
}