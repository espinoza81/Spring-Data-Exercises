package productshop.constant;

import java.nio.file.Path;

public enum PathFiles {
    ;
    public static final String IN_PATH = "src/main/resources/inFiles/";
    public static final String OUT_PATH = "src/main/resources/outFiles/";
    public static final String PRODUCT_IN_RANGE = "products-in-range.json";
    public static final String USERS_SOLD_PRODUCTS = "users-sold-products.json";
    public static final String CATEGORIES_BY_PRODUCTS = "categories-by-products.json";
    public static final String USERS_AND_PRODUCTS = "users-and-products.json";

    public static final Path USERS_FILE_PATH = Path.of(IN_PATH + "users.json");
    public static final Path CATEGORIES_FILE_PATH = Path.of(IN_PATH + "categories.json");
    public static final Path PRODUCTS_FILE_PATH = Path.of(IN_PATH + "products.json");
    public static final Path PRODUCTS_WITHOUT_BUYER_FILE_PATH = Path.of(OUT_PATH + PRODUCT_IN_RANGE);
    public static final Path USER_SOLD_PRODUCTS_FILE_PATH = Path.of(OUT_PATH + USERS_SOLD_PRODUCTS);
    public static final Path CATEGORIES_BY_PRODUCTS_FILE_PATH = Path.of(OUT_PATH + CATEGORIES_BY_PRODUCTS);
    public static final Path USER_AND_PRODUCTS_FILE_PATH = Path.of(OUT_PATH + USERS_AND_PRODUCTS);
}
