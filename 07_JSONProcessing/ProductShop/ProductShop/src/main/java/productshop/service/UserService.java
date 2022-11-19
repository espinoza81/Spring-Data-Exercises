package productshop.service;

import productshop.domain.user.UserSoldProductsDto;
import productshop.domain.user.UsersCountWrapperDto;

import java.io.IOException;
import java.util.List;

public interface UserService {

    void seedUsers() throws IOException;

    List<UserSoldProductsDto> findUsersWithSoldProducts();

    UsersCountWrapperDto findUsersSoldProductsWithCount();
}