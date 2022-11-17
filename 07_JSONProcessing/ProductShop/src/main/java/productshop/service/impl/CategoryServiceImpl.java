package productshop.service.impl;

import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import productshop.constant.PathFiles;
import productshop.domain.category.Category;
import productshop.domain.category.CategoryImportDto;
import productshop.repository.CategoryRepository;
import productshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private final Gson gson;

    private final ModelMapper mapper;

    @Autowired
    public CategoryServiceImpl(
            CategoryRepository categoryRepository,
            Gson gson,
            ModelMapper mapper) {
        this.categoryRepository = categoryRepository;
        this.gson = gson;
        this.mapper = mapper;
    }

    @Override
    public void seedCategories() throws IOException {
        if (categoryRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.CATEGORIES_FILE_PATH.toFile());

            final List<Category> categories = Arrays.stream(gson.fromJson(fileReader, CategoryImportDto[].class))
                    .map(CategoryImportDto -> mapper.map(CategoryImportDto, Category.class))
                    .toList();

            fileReader.close();

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }
}