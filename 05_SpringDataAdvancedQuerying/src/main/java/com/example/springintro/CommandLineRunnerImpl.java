package com.example.springintro;

import com.example.springintro.messages.MenuLines;
import com.example.springintro.messages.OutputMessages;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final MenuFunctions menuFunctions;
    private final Scanner scanner = new Scanner(System.in);
    private final String END = "End";

    @Autowired
    public CommandLineRunnerImpl(
            MenuFunctions menuFunctions) {
        this.menuFunctions = menuFunctions;
    }

    @Override
    public void run(String... args) throws Exception {
        this.menuFunctions.seedData();

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
        printMainMenu();

        final int mainMenu = Integer.parseInt(scanner.nextLine());

        String result;

        switch (mainMenu) {

            case 1:
                result = this.menuFunctions._01_booksTitlesByAgeRestriction();
                break;
            case 2:
                result = this.menuFunctions._02_booksTitlesWithCopiesLessThan();
                break;
            case 3:
                result = this.menuFunctions._03_booksTitlesWithGivenPrice();
                break;
            case 4:
                result = this.menuFunctions._04_booksReleaseNotInGivenYear();
                break;
            case 5:
                result = this.menuFunctions._05_booksReleaseBeforeGivenDate();
                break;
            case 6:
                result = this.menuFunctions._06_authorsFirstNameEndWith();
                break;
            case 7:
                result = this.menuFunctions._07_booksTitleContainsGivenString();
                break;
            case 8:
                result = this.menuFunctions._08_booksAuthorLastNameStart();
                break;
            case 9:
                result = this.menuFunctions._09_countBooksTitleLongerGivenNumber();
                break;
            case 10:
                result = this.menuFunctions._10_authorsWithTotalCopies();
                break;
            case 11:
                result = this.menuFunctions._11_bookWithTitleTypeRestrictionPrice();
                break;
            case 12:
                result = this.menuFunctions._12_increaseBookCopiesReleaseDateAfter();
                break;
            case 13:
                result = this.menuFunctions._13_deleteBooksWithCopiesLoverThan();
                break;
            case 14:
                result = this.menuFunctions._14_storedProcedureReturnCountBooksByAuthorName();
                break;
            case 0:
                result = END;
                break;
            default:
                result = OutputMessages.NO_SUCH_MENU;
        }
        return result.trim();
    }

    private void printMainMenu() {

        final StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
                append(MenuLines.MENU_TOP).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_01).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_02).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_03).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_04).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_05).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_06).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_07).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_08).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_09).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_10).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_11).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_12).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_13).append(System.lineSeparator()).
                append(MenuLines.MENU_PROBLEM_14).append(System.lineSeparator()).
                append(MenuLines.MENU_EXIT).append(System.lineSeparator()).
                append(MenuLines.MENU_BOTTOM);

        System.out.println(mainMenu);
    }
}