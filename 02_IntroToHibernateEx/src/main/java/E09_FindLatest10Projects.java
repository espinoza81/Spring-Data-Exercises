import entities.Project;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Comparator;

public class E09_FindLatest10Projects {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.
                createQuery("FROM Project p ORDER BY p.startDate DESC", Project.class).
                setMaxResults(10).
                getResultList().stream().
                sorted(Comparator.comparing(Project::getName)).
                forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}