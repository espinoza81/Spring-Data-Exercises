import entities.Address;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Scanner;

public class E06_AddingNewAddressAndUpdatingEmployee {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("soft_uni");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        String newAddress = "Vitoshka 15";
        Address address = new Address();
        address.setText(newAddress);
        entityManager.persist(address);

        Scanner console = new Scanner(System.in);
        String lastName = console.nextLine();

        entityManager.
                createQuery("UPDATE Employee e" +
                        " SET e.address = :address " +
                        " WHERE e.lastName = :last_name").
                setParameter("last_name", lastName).
                setParameter("address", address).
                executeUpdate();

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}