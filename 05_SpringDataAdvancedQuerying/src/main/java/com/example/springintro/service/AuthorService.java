package com.example.springintro.service;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNameWithTotalCopies;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

public interface AuthorService {
    void seedAuthors() throws IOException;

    Author getRandomAuthor();

    List<String> findAllFirstNameEndWith(String endWith);

    @Transactional
    List<String> findAllLastNameStartWithAllBookTitle (String startWith);
    List<AuthorNameWithTotalCopies> findAllAuthorsWithTotalCopies();
}