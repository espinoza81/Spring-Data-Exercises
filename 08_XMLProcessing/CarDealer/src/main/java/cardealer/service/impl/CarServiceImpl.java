package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.car.*;
import cardealer.domain.car.dtos.CarDto;
import cardealer.domain.car.dtos.CarToyotaDto;
import cardealer.domain.car.dtos.CarWithPartsDto;
import cardealer.domain.car.wrapper.CarImportWrapperDto;
import cardealer.domain.car.wrapper.CarPartsWrapper;
import cardealer.domain.car.wrapper.CarToyotaWrapper;
import cardealer.domain.part.Part;
import cardealer.repository.CarRepository;
import cardealer.repository.PartRepository;
import cardealer.service.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.LongStream;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final PartRepository partRepository;
    private final ModelMapper mapper;
    private final Gson gson;

    public CarServiceImpl(
            CarRepository carRepository,
            PartRepository partRepository,
            ModelMapper mapper,
            Gson gson) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedCars() throws IOException, JAXBException {
        if (carRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.CARS_FILE_PATH.toFile());

            final JAXBContext context = JAXBContext.newInstance(CarImportWrapperDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final CarImportWrapperDto carsDto = (CarImportWrapperDto) unmarshaller.unmarshal(fileReader);

            final List<Car> cars = carsDto
                    .getCars()
                    .stream()
                    .map(car -> mapper.map(car, Car.class))
                    .map(this::setRandomParts)
                    .toList();

            fileReader.close();

            this.carRepository.saveAllAndFlush(cars);
        }
    }

    @Override
    public CarToyotaWrapper getAllCarsByMaker(String maker) {

        List<CarToyotaDto> carToyota = this.carRepository
                .findByMakeOrderByModelAscTravelledDistanceDesc(maker)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> mapper.map(car, CarToyotaDto.class))
                .toList();

        return new CarToyotaWrapper(carToyota);
    }

    @Override
    @Transactional
    public CarPartsWrapper getAllCarWithParts() {

        List<CarWithPartsDto> carWithPartsDtos = this.carRepository
                .findAll()
                .stream()
                .map(car -> mapper.map(car, CarDto.class))
                .map(CarDto::carWithPartsDto)
                .toList();

        return new CarPartsWrapper(carWithPartsDtos);
    }

    public Car setRandomParts(Car car) {
        final Random random = new Random();

        final long numberOfParts = random.nextLong(10, 21);

        final Set<Part> parts = new HashSet<>();

        LongStream.range(0, numberOfParts)
                .forEach(number -> {
                    Part part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);

                    while (parts.contains(part)) {
                        part = this.partRepository.getRandomEntity().orElseThrow(NoSuchElementException::new);
                    }

                    parts.add(part);
                });

        car.setParts(parts);

        return car;
    }
}
