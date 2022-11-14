package gameshop.service.impl;

import gameshop.domain.game.Game;
import gameshop.domain.game.GameNameDTO;
import gameshop.domain.game.RegisterGameDTO;
import gameshop.domain.user.LoginDTO;
import gameshop.domain.user.RegisterUserDto;
import gameshop.domain.user.User;
import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;
import gameshop.service.ExecutorService;
import gameshop.service.GameService;
import gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Service
public class ExecutorServiceImpl implements ExecutorService {
    private final Scanner scanner;
    private final UserService userService;
    private final GameService gameService;

    @Autowired
    public ExecutorServiceImpl(
            Scanner scanner,
            UserService userService,
            GameService gameService){
        this.scanner = scanner;
        this.userService = userService;
        this.gameService = gameService;
    }

    public String executeCommand() {

        System.out.println(OutputMessages.ENTER_COMMAND);

        String[] input = scanner.nextLine().split("\\|");

        String command = input[0];

        String result = switch (command) {
            case REGISTER_USER -> registerUser(input);
            case LOGIN_USER -> loginUser(input);
            case LOGOUT -> userService.logout();
            case ADD_GAME -> addGame(input);
            case EDIT_GAME -> editGame(input);
            case DELETE_GAME -> deleteGame(Long.parseLong(input[1]));
            case ALL_GAMES -> gameService.allGames();
            case GAME_DETAILS -> gameService.detailsGame(input[1]);
            case OWNED_GAMES -> userService.ownedGames();
            case ADD_ITEM -> userService.addItem(input[1]);
            case REMOVE_ITEM -> userService.removeItem(input[1]);
            case BUE_ITEM -> userService.byItem();
            case END -> END;
            default -> OutputMessages.NO_SUCH_MENU;
        };

        return result.trim();
    }

    private String deleteGame(long id) {
        checkLoggedUser();

        GameNameDTO gameNameDTO = gameService.deleteGame(id);

        return String.format(OutputMessages.SUCCESSFUL_DELETE_GAME, gameNameDTO.getTitle());
    }

    private String editGame(String[] input) {
        checkLoggedUser();

        String title = gameService.editGame(input);

        return String.format(OutputMessages.SUCCESSFUL_EDITED_GAME, title);
    }

    private String addGame(String[] input) {
        checkLoggedUser();

        if(gameService.gameWithTitleExist(input[1])){
            throw new ValidationException(OutputMessages.GAME_EXIST);
        }

        RegisterGameDTO registerGameDTO = new RegisterGameDTO(input);

        Game game = gameService.register(registerGameDTO);

        return String.format(OutputMessages.SUCCESSFUL_ADD_GAME, game.getTitle());
    }

    private void checkLoggedUser() {
        User loggedUser = userService.getCurrentLoggedUser();

        if(loggedUser == null) {
            throw new ValidationException(OutputMessages.NO_USER_LOGGED_IN);
        }

        if(!loggedUser.isAdmin()) {
            throw new ValidationException(OutputMessages.USER_NOT_ADMIN);
        }
    }

    private String loginUser(String[] input) {
        LoginDTO loginDTO = new LoginDTO(input);

        User user = userService.login(loginDTO);

        return String.format(OutputMessages.SUCCESSFUL_LOGIN, user.getFullName());
    }

    private String registerUser(String[] input) {
        if(userService.userExistEmail(input[1])){
            throw new ValidationException(OutputMessages.USER_WITH_EMAIL_EXIST);
        }

        RegisterUserDto registerUserDto = new RegisterUserDto(input);

        User user = userService.register(registerUserDto);

        return String.format(OutputMessages.USER_REGISTER_SUCCESS, user.getFullName());
    }
}