package gameshop.domain.user;

import gameshop.domain.game.Game;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "full_name", nullable = false, length = 150)
    private String fullName;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "is_admin", nullable = false)
    private boolean admin;

    @ManyToMany
    private final Set<Game> games;

    public User(String fullName, String email, String password) {
        this();

        this.fullName = fullName;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.games = new HashSet<>();
        this.admin = false;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public Set<Game> getGames() {
        return Collections.unmodifiableSet(games);
    }
}