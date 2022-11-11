package gameshop.core;

import gameshop.messages.OutputMessages;
import gameshop.model.dto.UserDTO;
import gameshop.model.entity.Game;
import gameshop.model.entity.User;
import gameshop.service.GameService;
import gameshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class CommandMethod {
    private final UserService userService;
    private final GameService gameService;

    private final String DATE_FORMAT = "dd-MM-yyyy";

    private UserDTO currentUser;

    @Autowired
    public CommandMethod(
            UserService userService,
            GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    public String registerUser(String[] input) {

        String email = input[1];
        String password = input[2];
        String confirmPassword = input[3];
        String fullName = input[4];

        List<String> invalidDataMessage = userService.validateUserPart(email, password, confirmPassword);

        if (!invalidDataMessage.isEmpty()) {
            return invalidDataMessage
                    .stream().collect(Collectors.joining(System.lineSeparator()));
        }

        User user = new User(fullName, email, password);

        if (userService.userRepoSize() == 0) {
            user.setAdmin(true);
        }

        userService.save(user);


        return String.format(OutputMessages.USER_REGISTER_SUCCESS, fullName);
    }

    public String loginUser(String[] input) {
        if (currentUser != null){
            return OutputMessages.HAVE_LOGGED_USER;
        }
        String email = input[1];
        String password = input[2];

        Optional<UserDTO> exist = userService.userExist(email, password);

        if(exist.isPresent()) {
            currentUser = exist.get();
            return String.format(OutputMessages.SUCCESSFUL_LOGIN, currentUser.getFullName());
        }

        return OutputMessages.INCORRECT_USERNAME_PASSWORD;
    }

    public String logout() {
        if(currentUser == null) {
            return OutputMessages.NO_LOGGED_USER;
        }

        String loggedUserName = currentUser.getFullName();
        currentUser = null;

        return String.format(OutputMessages.SUCCESSFUL_LOGOUT, loggedUserName);
    }

    public String addGame(String[] input) {
        String noUserLoggedIn = checkUser();

        if (noUserLoggedIn != null) return noUserLoggedIn;

        String title = input[1];
        BigDecimal price = BigDecimal.valueOf(Double.parseDouble(input[2]));
        double sizeInMB = Double.parseDouble(input[3]);
        String youtubeId = input[4];
        String imageThumbnailURL = input[5];
        String description = input[6];
        LocalDate releaseDate = LocalDate.parse(input[7], DateTimeFormatter.ofPattern(DATE_FORMAT));

        List<String> invalidDataMessage =
                gameService.validateGamePart(title, price, sizeInMB, youtubeId, imageThumbnailURL, description);

        if (!invalidDataMessage.isEmpty()) {
            return invalidDataMessage
                    .stream().collect(Collectors.joining(System.lineSeparator()));
        }

        Game game = new Game(title, youtubeId, imageThumbnailURL, sizeInMB, price, description, releaseDate);

        gameService.save(game);

        return String.format(OutputMessages.SUCCESSFUL_ADD_GAME, title);
    }

    public String editGame(String[] input) {
        String noUserLoggedIn = checkUser();

        if (noUserLoggedIn != null) return noUserLoggedIn;

        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String deleteGame(String[] input) {
        String noUserLoggedIn = checkUser();

        if (noUserLoggedIn != null) return noUserLoggedIn;

        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String allGames(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String detailsGame(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String ownedGames(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String addItem(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }


    public String removeItem(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }

    public String buyItem(String[] input) {
        return OutputMessages.UNDER_CONSTRUCTION;
    }

    private String checkUser() {
        if(currentUser == null) {
            return OutputMessages.NO_USER_LOGGED_IN;
        }

        if(!currentUser.isAdmin()) {
            return OutputMessages.USER_NOT_ADMIN;
        }

        return null;
    }
}
