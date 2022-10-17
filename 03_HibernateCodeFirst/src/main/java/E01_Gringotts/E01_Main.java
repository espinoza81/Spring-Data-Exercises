package E01_Gringotts;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class E01_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Code-second");
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();

        WizardDeposits wizardDeposits = new WizardDeposits("Pesho", "Peshev", "proba", 44, "Pesho",
                (short) 33, "full", LocalDateTime.now(), BigDecimal.valueOf(1245.69), BigDecimal.valueOf(12.5),
                BigDecimal.valueOf(0.25), LocalDateTime.of(2025, 5, 5, 12, 15), false);

        entityManager.persist(wizardDeposits);

        entityManager.getTransaction().commit();
        entityManager.close();
    }
}