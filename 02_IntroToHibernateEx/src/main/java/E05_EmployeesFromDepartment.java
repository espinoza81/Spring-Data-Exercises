import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;

public class E05_EmployeesFromDepartment {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        String department = "Research and Development";

        List<Employee> employees = entityManager.
                createQuery("FROM Employee e" +
                        " WHERE e.department.name = :departmentName" +
                        " ORDER BY e.salary, e.id", Employee.class).
                setParameter("departmentName", department).
                getResultList();

        employees.forEach(e -> System.out.printf("%s %s from %s - $%.2f%n",
                e.getFirstName(), e.getLastName(), department, e.getSalary()));

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}