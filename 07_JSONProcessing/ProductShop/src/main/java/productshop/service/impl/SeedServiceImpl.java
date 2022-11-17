package productshop.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import productshop.service.CategoryService;
import productshop.service.ProductService;
import productshop.service.SeedService;
import productshop.service.UserService;

import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class SeedServiceImpl implements SeedService {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public SeedServiceImpl(
            UserService userService,
            ProductService productService,
            CategoryService categoryService){
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedData() throws IOException {
        userService.seedUsers();
        categoryService.seedCategories();
        productService.seedProducts();
    }
}