package bookshop.services;

import bookshop.models.Category;

import java.util.Set;

public interface CategoryService {
    Set<Category> getRandomCategories();
}