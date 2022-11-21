package productshop.service;

import productshop.domain.category.CategoryCountProductsDto;
import productshop.domain.category.XMLCategoryCountProductsDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface CategoryService {
    void seedCategories() throws IOException, JAXBException;

    XMLCategoryCountProductsDto allOrderByCountProducts();
}
