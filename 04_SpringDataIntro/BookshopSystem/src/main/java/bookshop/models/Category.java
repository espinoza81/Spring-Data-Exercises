package bookshop.models;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

//    @ManyToMany(mappedBy = "categories")
//    private Set<Book> books;

    public Category(String name) {
        this.name = name;
//        this.books = new HashSet<>();
    }

    public Category() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Set<Book> getBooks() {
//        return Collections.unmodifiableSet(books);
//    }
}