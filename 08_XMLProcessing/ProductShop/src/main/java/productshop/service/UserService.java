package productshop.service;

import productshop.domain.user.UsersCountWrapperDto;
import productshop.domain.user.XMLUsersSoldProductsWrapperDto;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface UserService {

    void seedUsers() throws IOException, JAXBException;

    XMLUsersSoldProductsWrapperDto findUsersWithSoldProducts();

    UsersCountWrapperDto findUsersSoldProductsWithCount();
}