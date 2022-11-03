package bookshop;

import bookshop.models.Author;
import bookshop.models.Book;
import bookshop.repositories.AuthorRepository;
import bookshop.repositories.BookRepository;
import bookshop.services.SeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class ConsoleRunner implements CommandLineRunner {
    private static final String BOOK_COUNT = "Total book count: %d%n";
    private static final String AUTHORS_BY_BOOK_BEFORE_COUNT = "Total authors with books released date before 1990: %d%n";
    private static final String AUTHORS_BOOK_COUNT = "%s %s -> %d%n";
    private static final String BOOKS_BY_AUTHOR_NAME = "Title: %s, release date: %s, copies %d.%n";
    private static final String AUTHOR_FIRST_NAME = "George";
    private static final String AUTHOR_LAST_NAME = "Powell";

    private final SeedService seedService;

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @Autowired
    public ConsoleRunner(SeedService seedService,
                         BookRepository bookRepository,
                         AuthorRepository authorRepository) {
        this.seedService = seedService;
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    private void _01_booksAfter() {
        LocalDate yearAfter = LocalDate.of(2000, 12, 31);

        List<Book> books = bookRepository.findByReleaseDateAfter(yearAfter);

        books.forEach(b -> System.out.println(b.getTitle()));
        System.out.printf(BOOK_COUNT, books.size());

    }

    private void _02_authorsWithBookBefore() {
        LocalDate yearBefore = LocalDate.of(1990, 1, 1);

        List<Author> authors = authorRepository.findDistinctByBooksReleaseDateBefore(yearBefore);
        authors.forEach(a -> System.out.println(a.getFirstName() + " " + a.getLastName()));
        System.out.printf(AUTHORS_BY_BOOK_BEFORE_COUNT, authors.size());
    }

    private void _03_allAuthorsWithBooksCount() {
        List<Author> authors = authorRepository.findAll();
        authors
                .stream()
                .sorted((l, r) -> r.getBooks().size() - l.getBooks().size())
                .forEach(a -> System.out.printf(AUTHORS_BOOK_COUNT, a.getFirstName(), a.getLastName(), a.getBooks().size()));
    }

    private void _04_allBooksFromGeorgePowell() {
        Author author = authorRepository.findByFirstNameAndLastName(AUTHOR_FIRST_NAME, AUTHOR_LAST_NAME);

        List<Book> books = bookRepository.findByAuthorOrderByReleaseDateDescTitleAsc(author);

        books.forEach(b -> System.out.printf(BOOKS_BY_AUTHOR_NAME, b.getTitle(), b.getReleaseDate(), b.getCopies()));
        System.out.printf(BOOK_COUNT, books.size());
    }

    @Override
    public void run(String... args) throws Exception {
        this.seedService.seedAll();

//        this._01_booksAfter();
//        this._02_authorsWithBookBefore();
//        this._03_allAuthorsWithBooksCount();
        this._04_allBooksFromGeorgePowell();
    }
}