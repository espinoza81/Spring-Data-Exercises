package bookshop.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "authors")
public class Author {
    //id, first name (optional) and last name
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @OneToMany(mappedBy = "author", targetEntity = Book.class, fetch = FetchType.EAGER)
    private Set<Book> books;

    public Author() {

    }

    public Author(String firstName, String lastName) {
//        new Author(lastName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = new HashSet<>();
    }

//    public Author(String lastName) {
//        this.lastName = lastName;
//        this.books = new HashSet<>();
//    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Book> getBooks() {
        return Collections.unmodifiableSet(books);
    }
}