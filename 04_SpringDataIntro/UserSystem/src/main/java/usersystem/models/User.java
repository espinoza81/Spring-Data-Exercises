package usersystem.models;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "users")
public class User {
    private static final String USERNAME_SIZE = "Username must between 4 and 30 symbols";
    private static final String INVALID_EMAIL_MESSAGE = "Invalid e-mail%n" +
            "Please register email in format <user>@<host>, where:%n" +
            "<user> is a sequence of letters and digits, where '.', '-' and '_' can appear between them.%n" +
            "<host> is a sequence of at least two words, separated by dots '.'.%n" +
            "Each word is sequence of letters and can have hyphens '-' between the letters.";
    private static final String EMAIL_REGEX =
            "^[A-Za-z0-9]+([-_.]?[A-Za-z0-9]+)*@([A-Za-z]+(-[A-Za-z]+)*)([.]([A-Za-z]+(-[A-Za-z]+)*))+$";
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Transient
    private String fullName;

    @Size(min = 4, max = 30, message = USERNAME_SIZE)
    @Column(unique = true, nullable = false)
    private String username;

    @NotNull
    @Password
    private String password;

    @Email(regexp = EMAIL_REGEX, message = INVALID_EMAIL_MESSAGE)
    @Column(unique = true, nullable = false)
    private String email;

    @Column(name = "registered_on")
    private final LocalDate registeredOn;

    @Column(name = "last_time_logged_in")
    private LocalDate lastTimeLoggedIn;

    @Min(1)
    @Max(120)
    @NotNull
    private int age;

    @Column(name = "is_deleted")
    private boolean forDeletion;

    @ManyToOne
    @JoinColumn(name = "born_town_id", nullable = false)
    private Town bornTown;

    @ManyToOne
    @JoinColumn(name = "living_town_id", nullable = false)
    private Town livingTown;

    @ManyToMany
    private final Set<User> friends;

    @OneToMany(mappedBy = "owner", targetEntity = Album.class)
    private final Set<Album> albums;

    protected User() {
        this.registeredOn = LocalDate.now();
        this.forDeletion = false;
        this.albums = new HashSet<>();
        this.friends = new HashSet<>();
    }

    public User(String firstName, String lastName, String username, String password,
                String email, int age, Town bornTown, Town livingTown, LocalDate lastTimeLoggedIn) {
        this();
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.email = email;
        this.age = age;
        this.bornTown = bornTown;
        this.livingTown = livingTown;
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public int getId() {
        return id;
    }

    public Town getLivingTown() {
        return livingTown;
    }

    public void setLivingTown(Town livingTown) {
        this.livingTown = livingTown;
    }


    public String getFullName() {
        return this.firstName + " " + this.lastName;
    }

    public Town getBornTown() {
        return bornTown;
    }

    public void setBornTown(Town bornTown) {
        this.bornTown = bornTown;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getRegisteredOn() {
        return registeredOn;
    }

    public LocalDate getLastTimeLoggedIn() {
        return lastTimeLoggedIn;
    }

    public int getAge() {
        return age;
    }

    public boolean isForDeletion() {
        return forDeletion;
    }

    public Set<User> getFriends() {
        return Collections.unmodifiableSet(friends);
    }

    public Set<Album> getAlbums() {
        return Collections.unmodifiableSet(albums);
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setLastTimeLoggedIn(LocalDate lastTimeLoggedIn) {
        this.lastTimeLoggedIn = lastTimeLoggedIn;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setForDeletion(boolean forDeletion) {
        this.forDeletion = forDeletion;
    }

    public void addAlbum(Album album) {
        this.albums.add(album);
    }

    public void addFriend(User friend) {
        this.friends.add(friend);
    }
}