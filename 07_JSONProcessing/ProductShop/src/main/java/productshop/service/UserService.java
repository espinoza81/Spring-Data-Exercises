package productshop.service;

import productshop.domain.user.User;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface UserService {

    void seedUsers() throws IOException;
}