package gameshop.service.impl;

import gameshop.domain.game.GameNameDTO;
import gameshop.domain.user.LoginDTO;
import gameshop.domain.user.RegisterUserDto;
import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;
import gameshop.domain.user.User;
import gameshop.repository.GameRepository;
import gameshop.repository.UserRepository;
import gameshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;
    private final ModelMapper mapper;
    private User currentUser;

    private Set<String> cart;

    private Set<String> ownedGames;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           GameRepository gameRepository,
                           ModelMapper mapper) {
        this.gameRepository = gameRepository;
        this.currentUser = null;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean userExistEmail(String email) {
        return userRepository.countByEmail(email) > 0;
    }

    @Override
    public User login(LoginDTO loginDTO) {

        checkLoggedUser(currentUser != null, OutputMessages.HAVE_LOGGED_USER);

        Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (user.isPresent()) {
            this.currentUser = user.get();
            this.cart = new HashSet<>();
//            this.ownedGames = userRepository
//                    .findUserGames(currentUser.getEmail())
//                    .orElse(new HashSet<>())
//                    .stream()
//                    .map(GameNameDTO::getTitle)
//                    .collect(Collectors.toSet());
        } else {
            throw new ValidationException(OutputMessages.INCORRECT_USERNAME_PASSWORD);
        }

        return getCurrentLoggedUser();
    }

    @Override
    public User getCurrentLoggedUser(){
        return this.currentUser;
    }

    @Override
    public User register(RegisterUserDto registerUserDto) {
        checkLoggedUser(currentUser != null, OutputMessages.HAVE_LOGGED_USER);

        User toRegister = mapper.map(registerUserDto, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public String logout() {
        checkLoggedUser(currentUser == null, OutputMessages.NO_LOGGED_USER);

        String loggedUserName = currentUser.getFullName();
        this.currentUser = null;
        this.cart = null;
        this.ownedGames = null;

        return String.format(OutputMessages.SUCCESSFUL_LOGOUT, loggedUserName);
    }

    private void checkLoggedUser(boolean currentUser, String noUserLoggedIn) {
        if(currentUser) {
            throw new ValidationException(noUserLoggedIn);
        }
    }

    @Override
    public String removeItem(String title) {
        checkLoggedUser(currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        if(!this.cart.remove(title)) {
            throw new ValidationException(OutputMessages.GAME_WITH_TITLE_NOT_EXIST);
        }

        return String.format(OutputMessages.REMOVE_ITEM, title);
    }

    @Override
    public String addItem(String title) {
        checkLoggedUser(currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        int gameInStore = gameRepository.countByTitleAndDeletedFalse(title);

        if(gameInStore <=0 ) {
            throw new ValidationException(OutputMessages.GAME_WITH_TITLE_NOT_EXIST);
        }

        if(ownedGames.contains(title)){
            throw new ValidationException(OutputMessages.USER_HAVE_GAME);
        }

        this.cart.add(title);
        return String.format(OutputMessages.ADD_ITEM, title);
    }

    @Override
    public String byItem() {
        checkLoggedUser(currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        if(this.cart.isEmpty()) {
            throw new ValidationException(OutputMessages.NO_ITEM_IN_CART);
        }


        return String.format(OutputMessages.BY_ITEM, "none");
    }

    @Override
    public String ownedGames() {
        checkLoggedUser(currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        return String.join(System.lineSeparator(), ownedGames);
    }
}