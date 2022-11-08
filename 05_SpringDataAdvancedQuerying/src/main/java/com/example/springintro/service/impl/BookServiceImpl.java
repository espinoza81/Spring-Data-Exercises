package com.example.springintro.service.impl;

import com.example.springintro.model.entity.*;
import com.example.springintro.repository.BookRepository;
import com.example.springintro.service.AuthorService;
import com.example.springintro.service.BookService;
import com.example.springintro.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private static final String BOOKS_FILE_PATH = "src/main/resources/files/books.txt";
    private final String DATE_FORMAT = "d/M/yyyy";
    private final String FORMAT_COUNT_BOOK_TITLE_LONGER = "There are %d books with longer title than %d symbols";
    private final String FORMAT_BOOKS_RELEASE_DATE_BEFORE = "Title: %s, edition type: %s - price: $%.2f";

    private final String DATE_FORMAT_RELEASE_BEFORE = "dd-MM-yyyy";
    private final String DATE_FORMAT_INCREASE_COPIES = "dd MMM yyyy";

    private final BookRepository bookRepository;
    private final AuthorService authorService;
    private final CategoryService categoryService;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService, CategoryService categoryService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
        this.categoryService = categoryService;
    }

    @Override
    public void seedBooks() throws IOException {

        if (bookRepository.count() > 0) {
            return;
        }

        Files
                .readAllLines(Path.of(BOOKS_FILE_PATH))
                .forEach(row -> {
                    String[] bookInfo = row.split("\\s+");

                    Book book = createBookFromInfo(bookInfo);

                    bookRepository.save(book);
                });
    }

    @Override
    public List<String> findAllBooksTitlesByAgeRestriction(String ageRestriction) {

        AgeRestriction restriction = AgeRestriction.valueOf(ageRestriction);

        return bookRepository.findByAgeRestrictionEquals(restriction)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksTitlesByEditionTypeAndCopiesLessThan(EditionType editionType, int copies) {

        return bookRepository.findByEditionTypeEqualsAndCopiesLessThan(editionType, copies)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksWithGivenPrice(Integer loverPrice, Integer higherPrice) {

        BigDecimal lover = BigDecimal.valueOf(loverPrice);
        BigDecimal higher = BigDecimal.valueOf(higherPrice);

        return bookRepository.findByPriceBeforeOrPriceAfter(lover, higher)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(b -> b.getTitle() + " - $" + b.getPrice())
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllBooksNotReleaseInGivenYear(String year) {
        return bookRepository.findByReleaseDateYearNot(year).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public List<String> findAllReleaseDateBefore(String dateFromConsole) {
        LocalDate releaseDate = extractDate(DATE_FORMAT_RELEASE_BEFORE, dateFromConsole);

        return bookRepository.findByReleaseDateBefore(releaseDate)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(b -> String.format(FORMAT_BOOKS_RELEASE_DATE_BEFORE,
                        b.getTitle(), b.getEditionType(), b.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> findAllTitleContainString(String contains) {
        return bookRepository.findByTitleContains(contains)
                .orElseThrow(NoSuchElementException::new)
                .stream()
                .map(Book::getTitle)
                .collect(Collectors.toList());
    }

    @Override
    public String findCountBookTitleLongerThan(int length) {
        int count = bookRepository.countByTitleCharLengthLongerThan(length).orElseThrow(NoSuchElementException::new);

        return String.format(FORMAT_COUNT_BOOK_TITLE_LONGER, count, length);
    }

    @Override
    public String bookTitleTypeRestrictionPrice(String title) {
        return bookRepository.bookWithTitleTypeRestrictionPrice(title)
                .orElseThrow(NoSuchElementException::new)
                .info();
    }

    @Override
    public Integer updateCopiesReleaseDateAfter(int copies, String dateFromConsole) {

        LocalDate releaseDate = extractDate(DATE_FORMAT_INCREASE_COPIES, dateFromConsole);

        return bookRepository.updateCopiesReleaseDateAfter(copies, releaseDate).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer deleteBooksCopiesLoverThen(int copies) {
        return bookRepository.deleteByCopiesLessThan(copies).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Integer selectBooksCountFromStoredProcedureByAuthorName(String name) {
        String[] nameParts = name.split("\\s+");
        String firstName = nameParts[0];
        String lastName = nameParts[1];
        return bookRepository.countBooksWrittenByAuthorStoredProcedure(firstName, lastName);
    }

    private LocalDate extractDate(String date_format, String dateFromConsole) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(date_format, Locale.ENGLISH);

        return LocalDate.parse(dateFromConsole, formatter);
    }

    private Book createBookFromInfo(String[] bookInfo) {

        EditionType editionType = EditionType.values()[Integer.parseInt(bookInfo[0])];
        LocalDate releaseDate = LocalDate
                .parse(bookInfo[1], DateTimeFormatter.ofPattern(DATE_FORMAT));
        Integer copies = Integer.parseInt(bookInfo[2]);
        BigDecimal price = new BigDecimal(bookInfo[3]);
        AgeRestriction ageRestriction = AgeRestriction
                .values()[Integer.parseInt(bookInfo[4])];
        String title = Arrays.stream(bookInfo)
                .skip(5)
                .collect(Collectors.joining(" "));

        Author author = authorService.getRandomAuthor();
        Set<Category> categories = categoryService
                .getRandomCategories();

        return new Book(editionType, releaseDate, copies, price, ageRestriction, title, author, categories);
    }
}