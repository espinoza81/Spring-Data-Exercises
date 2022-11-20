package productshop.service.impl;

import com.google.gson.Gson;
import productshop.constant.PathFiles;
import productshop.domain.user.*;
import productshop.repository.ProductRepository;
import productshop.repository.CategoryRepository;
import productshop.repository.UserRepository;
import productshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

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
    public void seedUsers() throws IOException, JAXBException {
        if (userRepository.count() == 0) {
            final FileReader fileReader = new FileReader(PathFiles.USERS_FILE_PATH_XML.toFile());

            final JAXBContext context = JAXBContext.newInstance(UsersImportXMLDto.class);
            final Unmarshaller unmarshaller = context.createUnmarshaller();
            final UsersImportXMLDto usersDto = (UsersImportXMLDto) unmarshaller.unmarshal(fileReader);

            final List<User> users = usersDto.getUsers()
                    .stream()
                    .map(user -> mapper.map(user, User.class))
                    .toList();

//            Arrays.stream(gson.fromJson(fileReader, UserImportDto[].class))
//                    .map(userImportDto -> mapper.map(userImportDto, User.class))
//                    .toList();

            fileReader.close();

            this.userRepository.saveAllAndFlush(users);
        }
    }

    @Override
    public XMLUsersSoldProductsWrapperDto findUsersWithSoldProducts() {

        List<XMLUserSoldProductsDto> userSoldProductsDtos = this.userRepository
                .findAllByOrderByLastNameAscFirstNameAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, XMLUserSoldProductsDto.class))
                .toList();

        userSoldProductsDtos
                .forEach(u ->
                        u.getSoldProducts()
                                .getProducts()
                                .removeIf(p -> p.getBuyerFirstName() == null && p.getBuyerLastName() == null));

        return new XMLUsersSoldProductsWrapperDto(userSoldProductsDtos);
    }

    @Override
    public UsersCountWrapperDto findUsersSoldProductsWithCount() {
        List<UserDto> users = this.userRepository
                .findAllByOrderByLastNameAscFirstNameAsc()
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .toList();

        users.
                forEach(u -> u.getSoldProducts()
                .removeIf(p -> p.getBuyer() == null));


        List<UserWrapperDetailsDto> usersWrapper =
                users
                        .stream()
                        .map(UserDto::userWrapperDetailsDto)
                        .toList();

        List<UserWrapperDetailsDto> sorted = usersWrapper
                .stream()
                .sorted(Comparator.comparing(UserWrapperDetailsDto::soldProductsCount)
                        .reversed()
                        .thenComparing(UserWrapperDetailsDto::getLastName))
                .toList();

        return new UsersCountWrapperDto(sorted);
    }
}