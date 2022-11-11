package gameshop.service;

import gameshop.model.dto.UserDTO;
import gameshop.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    long userRepoSize();

    void save(User user);

    List<String> validateUserPart(String email, String password, String confirmPassword);

    Optional<UserDTO> userExist(String email, String password);
}