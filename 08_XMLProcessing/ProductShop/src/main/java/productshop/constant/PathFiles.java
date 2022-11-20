package productshop.constant;

import java.nio.file.Path;

public enum PathFiles {
    ;
    public static final String IN_PATH_XML = "src/main/resources/inFiles/xml/";
    public static final String OUT_PATH_XML = "src/main/resources/outFiles/xml/";

    public static final String PRODUCT_IN_RANGE_XML = "products-in-range.xml";
    public static final String USERS_SOLD_PRODUCTS_XML = "users-sold-products.xml";
    public static final String CATEGORIES_BY_PRODUCTS_XML = "categories-by-products.xml";
    public static final String USERS_AND_PRODUCTS_XML = "users-and-products.xml";

    public static final Path USERS_FILE_PATH_XML = Path.of(IN_PATH_XML + "users.xml");
    public static final Path CATEGORIES_FILE_PATH_XML = Path.of(IN_PATH_XML + "categories.xml");
    public static final Path PRODUCTS_FILE_PATH_XML = Path.of(IN_PATH_XML + "products.xml");

    public static final Path PRODUCTS_WITHOUT_BUYER_FILE_PATH_XML = Path.of(OUT_PATH_XML + PRODUCT_IN_RANGE_XML);
    public static final Path USER_SOLD_PRODUCTS_FILE_PATH_XML = Path.of(OUT_PATH_XML + USERS_SOLD_PRODUCTS_XML);
    public static final Path CATEGORIES_BY_PRODUCTS_FILE_PATH_XML = Path.of(OUT_PATH_XML + CATEGORIES_BY_PRODUCTS_XML);
    public static final Path USER_AND_PRODUCTS_FILE_PATH_XML = Path.of(OUT_PATH_XML + USERS_AND_PRODUCTS_XML);


    public static final String IN_PATH_JSON = "src/main/resources/inFiles/json/";
    public static final String OUT_PATH_JSON = "src/main/resources/outFiles/json/";
    public static final String PRODUCT_IN_RANGE_JSON = "products-in-range.json";
    public static final String USERS_SOLD_PRODUCTS_JSON = "users-sold-products.json";
    public static final String CATEGORIES_BY_PRODUCTS_JSON = "categories-by-products.json";
    public static final String USERS_AND_PRODUCTS_JSON = "users-and-products.json";

    public static final Path USERS_FILE_PATH_JSON = Path.of(IN_PATH_JSON + "users.json");
    public static final Path CATEGORIES_FILE_PATH_JSON = Path.of(IN_PATH_JSON + "categories.json");
    public static final Path PRODUCTS_FILE_PATH_JSON = Path.of(IN_PATH_JSON + "products.json");

    public static final Path PRODUCTS_WITHOUT_BUYER_FILE_PATH_JSON = Path.of(OUT_PATH_JSON + PRODUCT_IN_RANGE_JSON);
    public static final Path USER_SOLD_PRODUCTS_FILE_PATH_JSON = Path.of(OUT_PATH_JSON + USERS_SOLD_PRODUCTS_JSON);
    public static final Path CATEGORIES_BY_PRODUCTS_FILE_PATH_JSON = Path.of(OUT_PATH_JSON + CATEGORIES_BY_PRODUCTS_JSON);
    public static final Path USER_AND_PRODUCTS_FILE_PATH_JSON = Path.of(OUT_PATH_JSON + USERS_AND_PRODUCTS_JSON);
}
