package usersystem.services;

import java.io.IOException;

public interface SeedService {

    void seedTowns() throws IOException;
    void seedUsers() throws IOException;

    default void seedAll() throws IOException {
        seedTowns();
        seedUsers();
    }
}