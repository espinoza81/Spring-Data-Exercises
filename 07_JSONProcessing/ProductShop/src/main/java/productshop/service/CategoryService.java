package productshop.service;

import productshop.domain.category.CategoryCountProductsDto;

import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException;

    List<CategoryCountProductsDto> allOrderByCountProducts();
}
