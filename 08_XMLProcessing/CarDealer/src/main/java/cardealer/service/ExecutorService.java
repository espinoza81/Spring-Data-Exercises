package cardealer.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface ExecutorService {
    String executeCommand() throws IOException, JAXBException;
}
