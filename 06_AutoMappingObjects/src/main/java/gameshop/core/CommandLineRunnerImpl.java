package gameshop.core;

import gameshop.messages.OutputMessages;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {


    private final Scanner scanner;
    private final CommandMethod commandMethod;

    private final String END = "End";

    @Autowired
    public CommandLineRunnerImpl(
            CommandMethod commandMethod,
            Scanner scanner) {
        this.commandMethod = commandMethod;
        this.scanner = scanner;
    }

    @Override
    public void run(String... args) throws Exception {

        while (true) {
            String result;
            try {
                result = processInput();

                if (result.equals(END)) {
                    break;
                }

            } catch (IOException | IllegalArgumentException | NullPointerException e) {
                result = e.getMessage();
            }

            System.out.println(result);
        }
    }

    private String processInput() throws IOException {

        System.out.println(OutputMessages.ENTER_COMMAND);

        String[] input = scanner.nextLine().split("\\|");

        String command = input[0];

        String result;

        switch (command) {

            case "RegisterUser":
                result = commandMethod.registerUser(input);
                break;
            case "LoginUser":
                result = commandMethod.loginUser(input);
                break;
            case "Logout":
                result = commandMethod.logout();
                break;
            case "AddGame":
                result = commandMethod.addGame(input);
                break;
            case "EditGame":
                result = commandMethod.editGame(input);
                break;
            case "DeleteGame":
                result = commandMethod.deleteGame(input);
                break;
            case "AllGames":
                result = commandMethod.allGames(input);
                break;
            case "DetailsGame":
                result = commandMethod.detailsGame(input);
                break;
            case "OwnedGames":
                result = commandMethod.ownedGames(input);
                break;
            case "AddItem":
                result = commandMethod.addItem(input);
                break;
            case "RemoveItem":
                result = commandMethod.removeItem(input);
                break;
            case "BuyItem":
                result = commandMethod.buyItem(input);
                break;
            case "End":
                result = END;
                break;
            default:
                result = OutputMessages.NO_SUCH_MENU;
        }
        return result.trim();
    }
}