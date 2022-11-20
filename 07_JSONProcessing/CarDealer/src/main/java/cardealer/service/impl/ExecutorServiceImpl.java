package cardealer.service.impl;

import cardealer.constant.PathFiles;
import cardealer.domain.car.CarToyotaDto;
import cardealer.domain.car.CarWithPartsDto;
import cardealer.domain.custumer.CustomerOrderBirthdateDto;
import cardealer.domain.supplier.LocalSupplierDto;
import cardealer.service.CarService;
import cardealer.service.CustomerService;
import cardealer.service.SupplierService;
import com.google.gson.Gson;
import cardealer.constant.MenuLines;
import cardealer.constant.OutputMessages;
import cardealer.service.ExecutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final static String MAKER = "Toyota";
    private final Scanner scanner;
    private final Gson gson;
    private final CustomerService customerService;
    private final CarService carService;
    private final SupplierService supplierService;


    @Autowired
    public ExecutorServiceImpl(
            Scanner scanner,
            Gson gson,
            CustomerService customerService,
            CarService carService,
            SupplierService supplierService) {
        this.scanner = scanner;
        this.gson = gson;
        this.customerService = customerService;
        this.carService = carService;
        this.supplierService = supplierService;
    }

    @Override
    public String executeCommand() throws IOException {

        printMainMenu();

        int mainMenu = Integer.parseInt(scanner.nextLine());

        String result = switch (mainMenu) {
            case 0 -> OutputMessages.END_MENU;
            case 1 -> _01_allCustomerOrderByBirthdate();
            case 2 -> _02_allCarsFromToyota();
            case 3 -> _03_allLocalSuppliers();
            case 4 -> _04_allCarsWithParts();
//            case 5 -> _05_usersAndSoldProducts();
//            case 6 -> _06_usersAndSoldProducts();
            default -> OutputMessages.NO_SUCH_MENU;
        };

        return result.trim();
    }

    private String _04_allCarsWithParts() throws IOException {
        List<CarWithPartsDto> carWithPartsDtos = this.carService.getAllCarWithParts();

        this.writeJsonToFile(carWithPartsDtos, PathFiles.CARS_AND_PARTS_FILE_PATH);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles._04_CARS_AND_PARTS;
    }

    private String _03_allLocalSuppliers() throws IOException {
        List<LocalSupplierDto> localSuppliers = this.supplierService.getAllLocalSuppliers();

        this.writeJsonToFile(localSuppliers, PathFiles.LOCAL_SUPPLIERS_FILE_PATH);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles._03_LOCAL_SUPPLIERS;
    }

    private String _02_allCarsFromToyota() throws IOException {
        List<CarToyotaDto> carsFromToyota = this.carService.getAllCarsByMaker(MAKER);

        this.writeJsonToFile(carsFromToyota, PathFiles.CARS_FROM_TOYOTA_FILE_PATH);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles._02_CARS_FROM_TOYOTA;
    }

    private String _01_allCustomerOrderByBirthdate() throws IOException {

        List<CustomerOrderBirthdateDto> allCustomers = this.customerService.getAllCustomers();

        this.writeJsonToFile(allCustomers, PathFiles.ORDERED_CUSTOMERS_FILE_PATH);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH + PathFiles._01_ORDERED_CUSTOMERS;
    }

    public void writeJsonToFile(Object object, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        gson.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();

    }

    private void printMainMenu() {

        StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
                append(MenuLines.MENU_TOP).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_01).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_02).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_03).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_04).append(System.lineSeparator()).
//                append(MenuLines.MENU_PROBLEM_05).append(System.lineSeparator()).
//                append(MenuLines.MENU_PROBLEM_06).append(System.lineSeparator()).
        append(MenuLines.MENU_EXIT).append(System.lineSeparator()).
                append(MenuLines.MENU_BOTTOM);

        System.out.println(mainMenu);
    }
}