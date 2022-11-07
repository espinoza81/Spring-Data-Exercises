package com.example.springintro;

import com.example.springintro.messages.MenuLines;
import com.example.springintro.messages.OutputMessages;
import com.example.springintro.model.entity.AuthorNameWithTotalCopies;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class CommandLineRunnerImpl implements CommandLineRunner {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    private final Scanner scanner = new Scanner(System.in);

    private final Integer BOOKS_COPIES = 5000;
    private final Integer LOVER_PRICE = 5;
    private final Integer HIGHER_PRICE = 40;
    private final String END = "End";

    @Autowired
    public CommandLineRunnerImpl(
            CategoryService categoryService,
            AuthorService authorService,
            BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedData();

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

        int mainMenu = Integer.parseInt(scanner.nextLine());


        String result = null;

        switch (mainMenu) {

            case 1:
                result = this._01_booksTitlesByAgeRestriction();
                break;
            case 2:
                result = this._02_booksTitlesWithCopiesLessThan();
                break;
            case 3:
                result = this._03_booksTitlesWithGivenPrice();
                break;
            case 4:
                result = this._04_booksReleaseNotInGivenYear();
                break;
            case 5:
                result = this._05_booksReleaseBeforeGivenDate();
                break;
            case 6:
                result = this._06_authorsFirstNameEndWith();
                break;
            case 7:
                result = this._07_booksTitleContainsGivenString();
                break;
            case 8:
                result = this._08_booksAuthorLastNameStart();
                break;
            case 9:
                result = this._09_countBooksTitleLongerGivenNumber();
                break;
            case 10:
                result = this._10_authorsWithTotalCopies();
                break;
            case 11:
                result = this._11_bookWithTitleTypeRestrictionPrice();
                break;
            case 12:
                result = this._12_increaseBookCopiesReleaseDateAfter();
                break;
            case 13:
                result = this._13_deleteBooksWithCopiesLoverThan();
                break;
            case 14:
                result = this._14_storedProcedureReturnCountBooksByAuthorName();
                break;
            case 0:
                result = END;
                break;
            default:
                result = OutputMessages.NO_SUCH_MENU;
        }
        return result.trim();
    }

    private String _14_storedProcedureReturnCountBooksByAuthorName() {
        //procedure in MySQL server
        //CREATE PROCEDURE `udp_books_written_by`(first_n VARCHAR(50), last_n VARCHAR(50))
        //BEGIN
        //	SELECT count(b.id)
        //	FROM books AS b
        //	JOIN authors AS a
        //	ON b.author_id = a.id
        //	WHERE first_name = first_n AND last_name = last_n;
        //END
        System.out.println(OutputMessages.READ_AUTHOR_NAME);

        String name = scanner.nextLine().trim();

        int count = bookService.selectBooksCountFromStoredProcedureByAuthorName(name);

        return String.format(OutputMessages.FORMAT_BOOKS_COUNT_BY_AUTHOR, name, count);
    }

    private String _13_deleteBooksWithCopiesLoverThan() {
        System.out.println(OutputMessages.READ_COPIES_TO_DELETE_BOOKS);

        int copies = Integer.parseInt(scanner.nextLine().trim());

        int deleteBooks = bookService.deleteBooksCopiesLoverThen(copies);

        return String.format(OutputMessages.DELETE_BOOKS_COPIES_LOVER_THAN, deleteBooks);
    }

    private String _12_increaseBookCopiesReleaseDateAfter() {
        System.out.println(OutputMessages.READ_RELEASE_DATE_AND_COPIES);

        String dateFromConsole = scanner.nextLine().trim();

        int copies = Integer.parseInt(scanner.nextLine().trim());

        int increaseBooks = bookService.updateCopiesReleaseDateAfter(copies, dateFromConsole);

        int totalCopies = increaseBooks * copies;

        return String.format(OutputMessages.FORMAT_UPDATE_COPIES, increaseBooks, dateFromConsole, totalCopies);
    }

    private String _11_bookWithTitleTypeRestrictionPrice() {
        System.out.println(OutputMessages.READ_TITLE);

        String title = scanner.nextLine().trim();

        return bookService.bookTitleTypeRestrictionPrice(title);
    }

    private String _10_authorsWithTotalCopies() {
        return authorService.findAllAuthorsWithTotalCopies()
                .stream()
                .map(AuthorNameWithTotalCopies::info)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    private String _09_countBooksTitleLongerGivenNumber() {
        System.out.println(OutputMessages.READ_SYMBOLS_TITLE_LONG_THAN);

        int countSymbols = Integer.parseInt(scanner.nextLine().trim());

        return bookService.findCountBookTitleLongerThan(countSymbols);
    }

    private String _08_booksAuthorLastNameStart() {

        System.out.println(OutputMessages.READ_AUTHOR_LAST_NAME_START);

        String startWith = scanner.nextLine().trim();

        return String.join(System.lineSeparator(), authorService.findAllLastNameStartWithAllBookTitle(startWith));
    }

    private String _07_booksTitleContainsGivenString() {

        System.out.println(OutputMessages.READ_BOOK_TITLE_CONTAINS);

        String contains = scanner.nextLine().trim();

        return String.join(System.lineSeparator(), bookService.findAllTitleContainString(contains));
    }

    private String _06_authorsFirstNameEndWith() {

        System.out.println(OutputMessages.READ_AUTHOR_FIRST_NAME_END);

        String endWith = scanner.nextLine().trim();

        return String.join(System.lineSeparator(), authorService.findAllFirstNameEndWith(endWith));
    }

    private String _05_booksReleaseBeforeGivenDate() {

        System.out.println(OutputMessages.READ_RELEASE_DATE);

        String dateFromConsole = scanner.nextLine().trim();

        return String.join(System.lineSeparator(), bookService.findAllReleaseDateBefore(dateFromConsole));
    }

    private String _04_booksReleaseNotInGivenYear() {

        System.out.println(OutputMessages.READ_RELEASE_YEAR);

        String year = scanner.nextLine().trim();

        return String.join(System.lineSeparator(), bookService.findAllBooksNotReleaseInGivenYear(year));
    }

    private String _03_booksTitlesWithGivenPrice() {

        return String.join(System.lineSeparator(), bookService.findAllBooksWithGivenPrice(LOVER_PRICE, HIGHER_PRICE));
    }

    private String _02_booksTitlesWithCopiesLessThan(){

        return String.join(System.lineSeparator(),
                bookService.findAllBooksTitlesByEditionTypeAndCopiesLessThan(EditionType.GOLD,BOOKS_COPIES));
    }

    private String _01_booksTitlesByAgeRestriction(){

        System.out.println(OutputMessages.READ_AGE_RESTRICTION);

        String ageRestriction = scanner.nextLine().toUpperCase().trim();

        return String.join(System.lineSeparator(), bookService.findAllBooksTitlesByAgeRestriction(ageRestriction));
    }

    private void printMainMenu() {

        StringBuilder mainMenu = new StringBuilder().append(System.lineSeparator()).
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

    private void seedData() throws IOException {

        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}