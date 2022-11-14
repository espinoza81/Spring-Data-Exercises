package gameshop.domain.order;

import gameshop.domain.game.Game;
import gameshop.domain.user.User;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private User buyer;

    @ManyToMany
    private final Set<Game> products;

    public Order(User buyer) {
        this();

        this.buyer = buyer;
    }

    public Order() {
        this.products = new HashSet<>();
        this.date = LocalDate.now();
    }

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User user) {
        this.buyer = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Game> getProducts() {
        return Collections.unmodifiableSet(products);
    }
}