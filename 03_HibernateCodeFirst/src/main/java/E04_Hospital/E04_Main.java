package E04_Hospital;

import E04_Hospital.core.ControllerImpl;
import E04_Hospital.core.EngineImpl;
import E04_Hospital.core.interfaces.Controller;
import E04_Hospital.io.ConsoleReader;
import E04_Hospital.io.ConsoleWriter;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class E04_Main {
    public static void main(String[] args) {
        EntityManagerFactory emf =
                Persistence.createEntityManagerFactory("Code-second");
        EntityManager entityManager = emf.createEntityManager();

        ConsoleReader reader = new ConsoleReader();
        ConsoleWriter writer = new ConsoleWriter();
        Controller controller = new ControllerImpl(reader, writer, entityManager);

        EngineImpl engine = new EngineImpl(reader, writer, controller);
        engine.run();

        entityManager.close();
    }
}