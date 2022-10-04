import entities.Address;
import entities.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E08_GetEmployeeWithProject {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner console = new Scanner(System.in);
        int id = Integer.parseInt(console.nextLine());

        Employee employee = entityManager.
                createQuery("FROM Employee e WHERE e.id = :id", Employee.class).
                setParameter("id", id).
                getSingleResult();

        System.out.println(employee);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}