package gameshop.model.entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Order extends BaseEntity{

    @Column(nullable = false)
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToMany
    private final Set<Game> games;

    public Order(User user) {
        this.date = LocalDate.now();
        this.user = user;
        this.games = new HashSet<>();
    }

    public Order() {
        this.games = new HashSet<>();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }
}