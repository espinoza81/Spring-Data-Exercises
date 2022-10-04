import entities.Address;
import entities.Town;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.Scanner;

public class E13_RemoveTowns {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        Scanner console = new Scanner(System.in);
        String town = console.nextLine();

        List<Address> addresses = entityManager.
                createQuery("SELECT a FROM Address a WHERE a.town.name = :town", Address.class).
                setParameter("town", town).
                getResultList();

        int deleteAddresses = addresses.size();

        if(deleteAddresses == 0){
            System.out.println("No such town");
            entityManager.close();
            return;
        }

        entityManager.getTransaction().begin();
        addresses.forEach(a -> {
            a.getEmployees().forEach(e -> e.setAddress(null));
            entityManager.remove(a);
        });

        Town townFromDB = entityManager.
                createQuery("SELECT t FROM Town t WHERE t.name = :town", Town.class).
                setParameter("town", town).
                getSingleResult();

        entityManager.remove(townFromDB);

        String address = deleteAddresses == 1 ? "address" : "addresses";
        System.out.printf("%d %s in %s deleted", deleteAddresses, address, town);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}