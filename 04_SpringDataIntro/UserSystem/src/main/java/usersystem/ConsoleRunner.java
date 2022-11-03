package usersystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import usersystem.models.User;
import usersystem.repositories.UserRepository;
import usersystem.services.SeedService;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Component
public class ConsoleRunner implements CommandLineRunner {

    private static final String EMAIL_FROM_CONSOLE = "Please fill e-mail provider:";
    private static final String LAST_LOGGED_DATE =
            "Delete users who have not been logged in after date of (date format d/M/yyyy):";
    private static final String NO_SUCH_USERS_BY_EMAIL = "There is no users with email from this provider";
    private static final String USER_WITH_EMAIL = "%s with username: %s has e-mail: %s";
    private static final String USER_TO_BE_DELETED = "%d inactive users marked to be deleted%n";
    private final SeedService seedService;

    private final UserRepository userRepository;

    public ConsoleRunner(SeedService seedService, UserRepository userRepository) {
        this.seedService = seedService;
        this.userRepository = userRepository;
    }

    private void findUsersByEmail() {
        System.out.println(EMAIL_FROM_CONSOLE);

        final String email = "@"+ new Scanner(System.in).nextLine();

        List<User> users = userRepository.findByEmailEndingWith(email);

        if(users.isEmpty()) {
            System.out.println(NO_SUCH_USERS_BY_EMAIL);
            return;
        }

        users.stream()
                .map(u -> String.format(USER_WITH_EMAIL, u.getFullName(), u.getUsername(), u.getEmail()))
                .forEach(System.out::println);
    }

    private void removeInactiveUsers() {
        System.out.println(LAST_LOGGED_DATE);

        String[] datePart = new Scanner(System.in).nextLine().split("/");

        final LocalDate lastLoggedInBefore =
                LocalDate.of(Integer.parseInt(datePart[2]), Integer.parseInt(datePart[1]), Integer.parseInt(datePart[0]));

        List<User> users = userRepository.findByLastTimeLoggedInIsBefore(lastLoggedInBefore);

        users.forEach(u -> {
            u.setForDeletion(true);
            userRepository.save(u);
        });

        System.out.printf(USER_TO_BE_DELETED, users.size());

        userRepository.deleteAllByForDeletion(true);

     }

    @Override
    public void run(String... args) throws Exception {
//        this.seedService.seedAll();
//        this.findUsersByEmail();
        this.removeInactiveUsers();
    }
}