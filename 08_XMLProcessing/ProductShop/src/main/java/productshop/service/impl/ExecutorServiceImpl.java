package productshop.service.impl;

import com.google.gson.Gson;
import productshop.constant.MenuLines;
import productshop.constant.OutputMessages;
import productshop.constant.PathFiles;
import productshop.domain.category.CategoryCountProductsDto;
import productshop.domain.product.XMLProductWithoutBuyerDto;
import productshop.domain.user.UsersCountWrapperDto;
import productshop.domain.user.XMLUsersSoldProductsWrapperDto;
import productshop.service.CategoryService;
import productshop.service.ExecutorService;
import productshop.service.ProductService;
import productshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final Scanner scanner;
    private final Gson gson;
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public ExecutorServiceImpl(
            Scanner scanner,
            Gson gson, UserService userService,
            ProductService productService,
            CategoryService categoryService) {
        this.scanner = scanner;
        this.gson = gson;
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public String executeCommand() throws IOException, JAXBException {

        printMainMenu();

        int mainMenu = Integer.parseInt(scanner.nextLine());

        String result = switch (mainMenu) {
            case 0 -> OutputMessages.END_MENU;
            case 1 -> _01_allProductsWithoutBuyer();
            case 2 -> _02_soldProductWithBuyers();
            case 3 -> _03_categoriesWithSoldProducts();
            case 4 -> _04_usersAndSoldProducts();
            default -> OutputMessages.NO_SUCH_MENU;
        };

        return result.trim();
    }

    private String _04_usersAndSoldProducts() throws IOException {
        UsersCountWrapperDto usersCountWrapperDto = this.userService.findUsersSoldProductsWithCount();

        this.writeJsonToFile(usersCountWrapperDto, PathFiles.USER_AND_PRODUCTS_FILE_PATH_JSON);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH_JSON + PathFiles.USERS_AND_PRODUCTS_JSON;
    }

    private String _03_categoriesWithSoldProducts() throws IOException {
        List<CategoryCountProductsDto> categoryCountProducts = this.categoryService.allOrderByCountProducts();

        this.writeJsonToFile(categoryCountProducts, PathFiles.CATEGORIES_BY_PRODUCTS_FILE_PATH_JSON);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH_JSON + PathFiles.CATEGORIES_BY_PRODUCTS_JSON;
    }

    private String _02_soldProductWithBuyers() throws IOException, JAXBException {
        XMLUsersSoldProductsWrapperDto userSoldProducts = this.userService.findUsersWithSoldProducts();

 //       this.writeJsonToFile(userSoldProducts, PathFiles.USER_SOLD_PRODUCTS_FILE_PATH_JSON);

        this.writeXMLToFile(userSoldProducts, PathFiles.USER_SOLD_PRODUCTS_FILE_PATH_XML);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH_JSON + PathFiles.USERS_SOLD_PRODUCTS_XML;
    }

    private String _01_allProductsWithoutBuyer() throws IOException, JAXBException {

        System.out.println(OutputMessages.BOTTOM_PRICE_RANGE);
        double bottom = Double.parseDouble(scanner.nextLine());

        System.out.println(OutputMessages.TOP_PRICE_RANGE);
        double top = Double.parseDouble(scanner.nextLine());

        final XMLProductWithoutBuyerDto allWithoutBuyer = this.productService.findAllWithoutBuyer(bottom, top);

//        this.writeJsonToFile(allWithoutBuyer, PathFiles.PRODUCTS_WITHOUT_BUYER_FILE_PATH_JSON);

        this.writeXMLToFile(allWithoutBuyer, PathFiles.PRODUCTS_WITHOUT_BUYER_FILE_PATH_XML);

        return OutputMessages.CHECK_THE_FILE + System.lineSeparator() +
                PathFiles.OUT_PATH_XML + PathFiles.PRODUCT_IN_RANGE_XML;
    }

    public <T> void writeJsonToFile(T object, Path filePath) throws IOException {
        final FileWriter fileWriter = new FileWriter(filePath.toFile());

        gson.toJson(object, fileWriter);

        fileWriter.flush();
        fileWriter.close();

    }

    public <T> void writeXMLToFile(T data, Path filePath) throws JAXBException {

        final File file = filePath.toFile();

        final JAXBContext context = JAXBContext.newInstance(data.getClass());
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.marshal(data, file);
    }

    private void printMainMenu() {

        StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
                append(MenuLines.MENU_TOP).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_01).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_02).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_03).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_04).append(System.lineSeparator()).
                append(MenuLines.MENU_EXIT).append(System.lineSeparator()).
                append(MenuLines.MENU_BOTTOM);

        System.out.println(mainMenu);
    }
}