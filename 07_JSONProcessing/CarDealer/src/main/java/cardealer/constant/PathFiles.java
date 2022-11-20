package cardealer.constant;

import java.nio.file.Path;

public enum PathFiles {
    ;
    public static final String IN_PATH = "src/main/resources/inFiles/";
    public static final String OUT_PATH = "src/main/resources/outFiles/";
    public static final String _01_ORDERED_CUSTOMERS = "ordered-customers.json";
    public static final String _02_CARS_FROM_TOYOTA = "toyota-cars.json";
    public static final String _03_LOCAL_SUPPLIERS = "local-suppliers.json";

    public static final String SUPPLIERS_FILE = "suppliers.json";
    public static final String PARTS_FILE = "parts.json";
    public static final String CARS_FILE = "cars.json";
    public static final String CUSTOMERS_FILE = "customers.json";

    public static final Path SUPPLIERS_FILE_PATH = Path.of(IN_PATH + SUPPLIERS_FILE);
    public static final Path PARTS_FILE_PATH = Path.of(IN_PATH + PARTS_FILE);
    public static final Path CARS_FILE_PATH = Path.of(IN_PATH + CARS_FILE);
    public static final Path CUSTOMERS_FILE_PATH = Path.of(IN_PATH + CUSTOMERS_FILE);

    public static final Path ORDERED_CUSTOMERS_FILE_PATH = Path.of(OUT_PATH + _01_ORDERED_CUSTOMERS);
    public static final Path CARS_FROM_TOYOTA_FILE_PATH = Path.of(OUT_PATH + _02_CARS_FROM_TOYOTA);
    public static final Path LOCAL_SUPPLIERS_FILE_PATH = Path.of(OUT_PATH + _03_LOCAL_SUPPLIERS);

}