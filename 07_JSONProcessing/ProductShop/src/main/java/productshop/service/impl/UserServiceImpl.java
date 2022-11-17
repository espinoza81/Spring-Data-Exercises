package productshop.service.impl;

import com.google.gson.Gson;
import productshop.constant.PathFiles;
import productshop.domain.user.User;
import productshop.domain.user.UserImportDto;
import productshop.repository.ProductRepository;
import productshop.repository.CategoryRepository;
import productshop.repository.UserRepository;
import productshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ModelMapper mapper;
    private final Gson gson;


    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           ProductRepository productRepository,
                           CategoryRepository categoryRepository, ModelMapper mapper, Gson gson) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
        this.gson = gson;
    }

    @Override
    public void seedUsers() throws IOException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.USERS_FILE_PATH.toFile());

            final List<User> users = Arrays.stream(gson.fromJson(fileReader, UserImportDto[].class))
                    .map(userImportDto -> mapper.map(userImportDto, User.class))
                    .toList();

            fileReader.close();

            this.userRepository.saveAllAndFlush(users);
        }
    }
}