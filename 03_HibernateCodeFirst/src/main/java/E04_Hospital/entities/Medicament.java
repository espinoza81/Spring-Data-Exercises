package E04_Hospital.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "_04_medicaments")
public class Medicament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, length = 50)
    private String name;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Visitation> visitations;

    @ManyToMany(mappedBy = "medicaments")
    private Set<Patient> patients;


    public Medicament() {
    }

    public Medicament(String name) {
        this.name = name;
        this.patients = new HashSet<>();
        this.visitations = new HashSet<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Visitation> getVisitations() {
        return Collections.unmodifiableSet(visitations);
    }

    public void addVisitation(Visitation visitation) {
        this.visitations.add(visitation);
    }

    public Set<Patient> getPatients() {
        return Collections.unmodifiableSet(patients);
    }

    public void addPatient(Patient patient){
        this.patients.add(patient);
    }
}