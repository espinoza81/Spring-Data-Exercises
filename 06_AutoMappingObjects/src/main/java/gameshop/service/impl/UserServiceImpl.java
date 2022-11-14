package gameshop.service.impl;

import gameshop.domain.game.Game;
import gameshop.domain.order.Order;
import gameshop.domain.user.LoginDTO;
import gameshop.domain.user.RegisterUserDto;
import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;
import gameshop.domain.user.User;
import gameshop.repository.GameRepository;
import gameshop.repository.OrderRepository;
import gameshop.repository.UserRepository;
import gameshop.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final GameRepository gameRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;
    private User currentUser;

    private Set<String> cart;

    private Set<String> ownedGames;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           GameRepository gameRepository,
                           OrderRepository orderRepository, ModelMapper mapper) {
        this.gameRepository = gameRepository;
        this.orderRepository = orderRepository;
        this.currentUser = null;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean userExistEmail(String email) {
        return userRepository.countByEmail(email) > 0;
    }

    @Transactional
    @Override
    public User login(LoginDTO loginDTO) {

        checkLoggedUser(this.currentUser != null, OutputMessages.HAVE_LOGGED_USER);

        Optional<User> user = userRepository.findByEmailAndPassword(loginDTO.getEmail(), loginDTO.getPassword());

        if (user.isPresent()) {
            this.currentUser = user.get();
            this.cart = new HashSet<>();
            this.ownedGames = getOwnedGames();
        } else {
            throw new ValidationException(OutputMessages.INCORRECT_USERNAME_PASSWORD);
        }

        return getCurrentLoggedUser();
    }

    private Set<String> getOwnedGames() {
        return this.currentUser
                .getGames().stream()
                .map(Game::getTitle)
                .collect(Collectors.toSet());
    }

    @Override
    public User getCurrentLoggedUser(){
        return this.currentUser;
    }

    @Override
    public User register(RegisterUserDto registerUserDto) {
        checkLoggedUser(this.currentUser != null, OutputMessages.HAVE_LOGGED_USER);

        User toRegister = this.mapper.map(registerUserDto, User.class);

        long userCount = this.userRepository.count();

        if (userCount == 0) {
            toRegister.setAdmin(true);
        }

        return this.userRepository.save(toRegister);
    }

    @Override
    public String logout() {
        checkLoggedUser(this.currentUser == null, OutputMessages.NO_LOGGED_USER);

        String loggedUserName = this.currentUser.getFullName();
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
        checkLoggedUser(this.currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        if(!this.cart.remove(title)) {
            throw new ValidationException(OutputMessages.NO_SUCH_GAME_IN_CART);
        }

        return String.format(OutputMessages.REMOVE_ITEM, title);
    }

    @Override
    public String addItem(String title) {
        checkLoggedUser(this.currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        int gameInStore = gameRepository.countByTitleAndDeletedFalse(title);

        if(gameInStore <=0 ) {
            throw new ValidationException(OutputMessages.GAME_WITH_TITLE_NOT_EXIST);
        }
        
        if(this.ownedGames.contains(title)){
            throw new ValidationException(OutputMessages.USER_HAVE_GAME);
        }

        this.cart.add(title);
        
        return String.format(OutputMessages.ADD_ITEM, title);
    }

    @Transactional
    @Override
    public String buyItem() {
        checkLoggedUser(this.currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        if(this.cart.isEmpty()) {
            throw new ValidationException(OutputMessages.NO_ITEM_IN_CART);
        }

        Set<Game> gameToBuy = gameRepository.findByTitleIn(this.cart);

        this.currentUser.getGames().addAll(gameToBuy);
        userRepository.save(this.currentUser);
        this.ownedGames = getOwnedGames();

        Order order = new Order(this.currentUser, gameToBuy);

        orderRepository.save(order);

        this.cart.clear();

        return String.format(OutputMessages.BY_ITEM,
                gameToBuy.stream()
                        .map(g -> "\t-" + g.getTitle())
                        .collect(Collectors.joining(System.lineSeparator())));
    }

    @Override
    public String ownedGames() {
        checkLoggedUser(currentUser == null, OutputMessages.NO_USER_LOGGED_IN);

        return String.join(System.lineSeparator(), ownedGames);
    }
}