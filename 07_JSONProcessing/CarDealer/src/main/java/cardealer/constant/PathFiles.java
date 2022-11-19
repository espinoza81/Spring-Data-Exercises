package cardealer.constant;

import java.nio.file.Path;

public enum PathFiles {
    ;
    public static final String IN_PATH = "src/main/resources/inFiles/";
    public static final String OUT_PATH = "src/main/resources/outFiles/";
    public static final String PRODUCT_IN_RANGE = "products-in-range.json";

    public static final String SUPPLIERS_FILE = "suppliers.json";
    public static final String PARTS_FILE = "parts.json";
    public static final String CARS_FILE = "cars.json";
    public static final String CUSTOMERS_FILE = "customers.json";

    public static final Path SUPPLIERS_FILE_PATH = Path.of(IN_PATH + SUPPLIERS_FILE);
    public static final Path PARTS_FILE_PATH = Path.of(IN_PATH + PARTS_FILE);
    public static final Path CARS_FILE_PATH = Path.of(IN_PATH + CARS_FILE);
    public static final Path CUSTOMERS_FILE_PATH = Path.of(IN_PATH + CUSTOMERS_FILE);

    public static final Path PRODUCTS_WITHOUT_BUYER_FILE_PATH = Path.of(OUT_PATH + PRODUCT_IN_RANGE);

}
