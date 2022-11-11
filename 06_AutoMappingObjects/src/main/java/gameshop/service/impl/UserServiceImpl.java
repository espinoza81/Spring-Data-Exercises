package gameshop.service.impl;

import gameshop.core.PasswordValidator;
import gameshop.messages.OutputMessages;
import gameshop.model.dto.UserDTO;
import gameshop.model.entity.User;
import gameshop.repository.UserRepository;
import gameshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public long userRepoSize() {
        return userRepository.count();
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    public List<String> validateUserPart(String email, String password, String confirmPassword) {


        if (!password.equals(confirmPassword)) {
            return List.of(OutputMessages.PASSWORDS_DONT_MACH);
        }

        if(!OutputMessages.EMAIL_REGEX.matcher(email).find()) {
            return List.of(OutputMessages.INVALID_EMAIL_MESSAGE);
        }

        if(userRepository.countByEmail(email) > 0){
            return List.of(OutputMessages.USER_EXIST);
        }

        PasswordValidator passwordValidator = new PasswordValidator();
        if(!passwordValidator.isValid(password)) {
            return passwordValidator.getInvalidParameters();
        }

        return new ArrayList<>();
    }

    @Override
    public Optional<UserDTO> userExist(String email, String password) {
        return userRepository.findByEmailAndPassword(email, password);
    }
}