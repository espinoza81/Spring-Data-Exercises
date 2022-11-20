package productshop.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface SeedService {
    void seedData() throws IOException, JAXBException;
}
