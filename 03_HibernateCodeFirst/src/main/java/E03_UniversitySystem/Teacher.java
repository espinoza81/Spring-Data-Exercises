package E03_UniversitySystem;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Set;

@Entity(name = "_03_teachers")
public class Teacher extends Person{

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "salary_per_hour", nullable = false)
    private BigDecimal salaryPerHour;

    @OneToMany(mappedBy = "teacher")
    private Set<Course> courses;

    public Teacher() {
        super();
    }

    public Teacher(String firstName, String lastName, String phoneNumber, String email, BigDecimal salaryPerHour) {
        super(firstName, lastName, phoneNumber);
        this.email = email;
        this.salaryPerHour = salaryPerHour;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getSalaryPerHour() {
        return salaryPerHour;
    }

    public void setSalaryPerHour(BigDecimal salaryPerHour) {
        this.salaryPerHour = salaryPerHour;
    }
}
