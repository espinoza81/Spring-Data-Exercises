package gameshop.service;

import gameshop.domain.user.LoginDTO;
import gameshop.domain.user.RegisterUserDto;
import gameshop.domain.user.User;

public interface UserService {
    boolean userExistEmail(String email);

    String logout();

    User login(LoginDTO loginDTO);

    User register(RegisterUserDto registerUserDto);

    User getCurrentLoggedUser();

    String addItem(String title);

    String removeItem(String title);

    String buyItem();

    String ownedGames();
}