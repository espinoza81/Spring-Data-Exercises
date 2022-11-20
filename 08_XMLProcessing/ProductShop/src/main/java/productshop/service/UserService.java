package productshop.service;

import productshop.domain.user.UserSoldProductsDto;
import productshop.domain.user.UsersCountWrapperDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers() throws IOException, JAXBException;

    List<UserSoldProductsDto> findUsersWithSoldProducts();

    UsersCountWrapperDto findUsersSoldProductsWithCount();
}