package gameshop.service;

public interface ExecutorService {
    String REGISTER_USER = "RegisterUser";
    String LOGIN_USER = "LoginUser";
    String LOGOUT = "Logout";
    String ADD_GAME = "AddGame";
    String EDIT_GAME = "EditGame";
    String DELETE_GAME = "DeleteGame";
    String ALL_GAMES = "AllGames";
    String GAME_DETAILS = "DetailGame";
    String OWNED_GAMES = "OwnedGames";
    String ADD_ITEM = "AddItem";
    String REMOVE_ITEM = "RemoveItem";
    String BUE_ITEM = "BuyItem";
    String END = "End";

    String executeCommand();
}
