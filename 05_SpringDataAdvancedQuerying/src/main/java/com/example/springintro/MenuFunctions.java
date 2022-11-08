package com.example.springintro;

import com.example.springintro.messages.OutputMessages;
import com.example.springintro.model.entity.AuthorNameWithTotalCopies;
import com.example.springintro.model.entity.EditionType;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Scanner;
import java.util.stream.Collectors;

@Component
public class MenuFunctions {

    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    private final Scanner scanner = new Scanner(System.in);

    private final Integer BOOKS_COPIES = 5000;
    private final Integer LOVER_PRICE = 5;
    private final Integer HIGHER_PRICE = 40;

    @Autowired
    public MenuFunctions(
            CategoryService categoryService,
            AuthorService authorService,
            BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    String _14_storedProcedureReturnCountBooksByAuthorName() {
        //procedure in MySQL server

        // CREATE PROCEDURE `udp_books_written_by`(
        //        IN first_n VARCHAR(255),
        //        IN last_n VARCHAR(255),
        //        OUT copies INT)
        // BEGIN
        //	    SELECT count(b.id) INTO copies
        //	    FROM books AS b
        //	    JOIN authors AS a
        //	    ON b.author_id = a.id
        //	    WHERE first_name = first_n AND last_name = last_n;
        // END

        final String name = getStringFromConsole(OutputMessages.READ_AUTHOR_NAME);

        final int count = bookService.selectBooksCountFromStoredProcedureByAuthorName(name);

        return String.format(OutputMessages.FORMAT_BOOKS_COUNT_BY_AUTHOR, name, count);
    }

    String _13_deleteBooksWithCopiesLoverThan() {
        System.out.println(OutputMessages.READ_COPIES_TO_DELETE_BOOKS);

        final int copies = Integer.parseInt(scanner.nextLine().trim());

        final int deleteBooks = bookService.deleteBooksCopiesLoverThen(copies);

        return String.format(OutputMessages.DELETE_BOOKS_COPIES_LOVER_THAN, deleteBooks);
    }

    String _12_increaseBookCopiesReleaseDateAfter() {
        System.out.println(OutputMessages.READ_RELEASE_DATE_AND_COPIES);

        final String dateFromConsole = scanner.nextLine().trim();

        final int copies = Integer.parseInt(scanner.nextLine().trim());

        final int increaseBooks = bookService.updateCopiesReleaseDateAfter(copies, dateFromConsole);

        final int totalCopies = increaseBooks * copies;

        return String.format(OutputMessages.FORMAT_UPDATE_COPIES, increaseBooks, dateFromConsole, totalCopies);
    }

    String _11_bookWithTitleTypeRestrictionPrice() {

        final String title = getStringFromConsole(OutputMessages.READ_TITLE);

        return bookService.bookTitleTypeRestrictionPrice(title);
    }

    String _10_authorsWithTotalCopies() {
        return authorService.findAllAuthorsWithTotalCopies()
                .stream()
                .map(AuthorNameWithTotalCopies::info)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    String _09_countBooksTitleLongerGivenNumber() {
        System.out.println(OutputMessages.READ_SYMBOLS_TITLE_LONG_THAN);

        final int countSymbols = Integer.parseInt(scanner.nextLine().trim());

        return bookService.findCountBookTitleLongerThan(countSymbols);
    }

    String _08_booksAuthorLastNameStart() {

        final String startWith = getStringFromConsole(OutputMessages.READ_AUTHOR_LAST_NAME_START);

        return String.join(System.lineSeparator(), authorService.findAllLastNameStartWithAllBookTitle(startWith));
    }

    String _07_booksTitleContainsGivenString() {

        final String contains = getStringFromConsole(OutputMessages.READ_BOOK_TITLE_CONTAINS);

        return String.join(System.lineSeparator(), bookService.findAllTitleContainString(contains));
    }

    String _06_authorsFirstNameEndWith() {

        final String endWith = getStringFromConsole(OutputMessages.READ_AUTHOR_FIRST_NAME_END);

        return String.join(System.lineSeparator(), authorService.findAllFirstNameEndWith(endWith));
    }

    String _05_booksReleaseBeforeGivenDate() {

        final String dateFromConsole = getStringFromConsole(OutputMessages.READ_RELEASE_DATE);

        return String.join(System.lineSeparator(), bookService.findAllReleaseDateBefore(dateFromConsole));
    }

    String _04_booksReleaseNotInGivenYear() {

        final String year = getStringFromConsole(OutputMessages.READ_RELEASE_YEAR);

        return String.join(System.lineSeparator(), bookService.findAllBooksNotReleaseInGivenYear(year));
    }

    String _03_booksTitlesWithGivenPrice() {

        return String.join(System.lineSeparator(), bookService.findAllBooksWithGivenPrice(LOVER_PRICE, HIGHER_PRICE));
    }

    String _02_booksTitlesWithCopiesLessThan(){

        return String.join(System.lineSeparator(),
                bookService.findAllBooksTitlesByEditionTypeAndCopiesLessThan(EditionType.GOLD,BOOKS_COPIES));
    }

    String _01_booksTitlesByAgeRestriction(){

        System.out.println(OutputMessages.READ_AGE_RESTRICTION);

        final String ageRestriction = scanner.nextLine().toUpperCase().trim();

        return String.join(System.lineSeparator(), bookService.findAllBooksTitlesByAgeRestriction(ageRestriction));
    }

    @NotNull
    private String getStringFromConsole(String message) {
        System.out.println(message);

        return scanner.nextLine().trim();
    }

    void seedData() throws IOException {

        categoryService.seedCategories();
        authorService.seedAuthors();
        bookService.seedBooks();
    }
}
