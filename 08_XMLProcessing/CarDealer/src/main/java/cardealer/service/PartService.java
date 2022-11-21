package cardealer.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface PartService {
    void seedPart() throws IOException, JAXBException;
}
