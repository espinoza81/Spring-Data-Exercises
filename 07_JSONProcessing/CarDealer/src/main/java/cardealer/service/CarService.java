package cardealer.service;

import cardealer.domain.car.CarToyotaDto;
import cardealer.domain.car.CarWithPartsDto;

import java.io.IOException;
import java.util.List;

public interface CarService {
    void seedCars() throws IOException;

    List<CarToyotaDto> getAllCarsByMaker(String maker);

    List<CarWithPartsDto> getAllCarWithParts();
}
