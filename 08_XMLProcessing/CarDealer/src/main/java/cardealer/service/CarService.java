package cardealer.service;

import cardealer.domain.car.wrapper.CarPartsWrapper;
import cardealer.domain.car.wrapper.CarToyotaWrapper;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws IOException, JAXBException;

    CarToyotaWrapper getAllCarsByMaker(String maker);

    CarPartsWrapper getAllCarWithParts();
}
