package gameshop.messages;

import java.util.regex.Pattern;

public class OutputMessages {
    public static final String INVALID_EMAIL_MESSAGE = "Incorrect email.";
    public static final Pattern EMAIL_REGEX = Pattern.compile(
            "^[A-Za-z0-9]+([-_.]?[A-Za-z0-9]+)*@([A-Za-z]+(-[A-Za-z]+)*)([.]([A-Za-z]+(-[A-Za-z]+)*))+$");
    public static final Pattern YOUTUBE_ID_REGEX = Pattern.compile(
            "^(?=[a-zA-z0-9]*[A-Z])(?=[a-zA-z0-9]*[0-9])(?=[a-zA-z0-9]*[a-z])[a-zA-z0-9]{11}$");

    public static final String PASSWORD_CANNOT_BE_NULL = "Password cannot be null!";
    public static final String PASSWORD_TOO_SHORT = "Password too short!";
    public static final String PASSWORD_TOO_LONG = "Password too long!";
    public static final String PASSWORD_SHOULD_CONTAIN_LOWERCASE_LETTER = "Password should contain lowercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_UPPERCASE_LETTER = "Password should contain uppercase letter!";
    public static final String PASSWORD_SHOULD_CONTAIN_DIGIT = "Password should contain digit!";
    public static final String PASSWORDS_DONT_MACH = "Passwords don`t mach";


    public static final String GAME_TITLE_TOO_LONG = "Title too long!";
    public static final String GAME_TITLE_TOO_SHORT = "Title too short!";

    public static final String NO_SUCH_MENU = "No such command";
    public static final String ENTER_COMMAND = "Please enter a command. End for exit";
    public static final String UNDER_CONSTRUCTION = "Under construction";

    public static final String USER_REGISTER_SUCCESS = "%s was registered";
    public static final String USER_WITH_EMAIL_EXIST = "User with this e-mail already exist";
    public static final String SUCCESSFUL_LOGIN = "Successfully logged in %s";
    public static final String INCORRECT_USERNAME_PASSWORD = "Incorrect username / password";
    public static final String NO_LOGGED_USER = "Cannot log out. No user was logged in.";
    public static final String SUCCESSFUL_LOGOUT = "User %s successfully logged out.";
    public static final String HAVE_LOGGED_USER = "There is a user logged into the system. Please first log out.";
    public static final String NO_USER_LOGGED_IN = "No user logged in";
    public static final String USER_NOT_ADMIN = "The logged user is not admin";
    public static final String TITLE_MUST_START_WITH_UPPERCASE = "Title must start with uppercase";
    public static final String PRICE_NOT_VALID = "The price must be greater than zero";
    public static final String SIZE_NOT_VALID = "The size of game must be greater than zero";

    public static final String INVALID_YOUTUBE_ID =
            "YouTube ID must be long 11 characters and consists only upper and lower case alphabets and numeric values";
    public static final String WRONG_THUMBNAIL_URL = "Wrong thumbnail image URL";
    public static final String DESCRIPTION_TOO_SHORT = "Description too short!";
    public static final String GAME_EXIST = "Game with this title already exist";
    public static final String SUCCESSFUL_ADD_GAME = "Added %s";
    public static final String SUCCESSFUL_EDITED_GAME = "Edited %s";
    public static final String SUCCESSFUL_DELETE_GAME = "Deleted %s";
    public static final String GAME_NOT_EXIST = "No game with id %d, or the game is deleted from catalogue";
    public static final String GAME_WITH_TITLE_NOT_EXIST = "No game with this title, or the game is deleted from catalogue";
    public static final String NO_SUCH_FIELD = "No such property";
    public static final String EXIT_PROGRAM = "You are exit the program";
    public static final String BY_ITEM = "Successfully bought games: %n%s";
    public static final String REMOVE_ITEM = "%s removed from cart.";
    public static final String ADD_ITEM = "%s added to cart.";
    public static final String USER_HAVE_GAME = "User already have the game.";
    public static final String NO_ITEM_IN_CART = "There are no games in the shopping cart";
}