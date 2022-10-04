import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E03_ContainsEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner console = new Scanner(System.in);
        String[] employeeToFind = console.nextLine().split("\\s+");
        String firstName = employeeToFind[0];
        String lastName = employeeToFind[1];

        Long record = entityManager.
                createQuery("SELECT count(e) FROM Employee e WHERE e.firstName = :first_name AND e.lastName = :last_name", Long.class).
                setParameter("first_name", firstName).
                setParameter("last_name", lastName).
                getSingleResult(); //Long.class because count return number

        System.out.println(record == 0? "No": "Yes");

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}