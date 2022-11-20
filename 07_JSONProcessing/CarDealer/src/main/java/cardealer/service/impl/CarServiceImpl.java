package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.car.Car;
import cardealer.domain.car.CarImportDto;
import cardealer.domain.car.CarToyotaDto;
import cardealer.domain.part.Part;
import cardealer.repository.CarRepository;
import cardealer.repository.PartRepository;
import cardealer.service.CarService;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

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
    public void seedCars() throws IOException {
        if(carRepository.count() == 0){
            final FileReader fileReader = new FileReader(PathFiles.CARS_FILE_PATH.toFile());

            List<Car> cars = Arrays.stream(gson.fromJson(fileReader, CarImportDto[].class))
                    .map(carImportDto -> mapper.map(carImportDto, Car.class))
                    .map(this::setRandomParts)
                    .toList();

            fileReader.close();

            this.carRepository.saveAllAndFlush(cars);
        }
    }

    @Override
    public List<CarToyotaDto> getAllCarsByMaker(String maker) {
        return this.carRepository
                .findByMakeOrderByModelAscTravelledDistanceDesc(maker)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(car -> mapper.map(car, CarToyotaDto.class))
                .toList();
    }

    public Car setRandomParts(Car car){
        final Random random = new Random();

        final long numberOfParts = random.nextLong(3,6);

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
