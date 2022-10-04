import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;

public class E02_ChangeCasing {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Query from_query = entityManager.createQuery("FROM Town t", Town.class);
        List<Town> resultList = from_query.getResultList();

        resultList.stream().
                filter(t -> t.getName().length() <= 5).
                forEach(t -> {
                    t.setName(t.getName().toUpperCase());
                    entityManager.persist(t);
                });

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}