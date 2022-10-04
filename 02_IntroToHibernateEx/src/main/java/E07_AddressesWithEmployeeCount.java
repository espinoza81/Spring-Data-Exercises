import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E07_AddressesWithEmployeeCount {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        entityManager.
                createQuery("FROM Address a ORDER BY a.employees.size DESC", Address.class).
                setMaxResults(10).
                getResultList().
                forEach(System.out::println);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}