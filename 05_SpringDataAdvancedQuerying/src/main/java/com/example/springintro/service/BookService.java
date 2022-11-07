package com.example.springintro.service;

import com.example.springintro.model.entity.EditionType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public interface BookService {
    void seedBooks() throws IOException;

    List<String> findAllBooksTitlesByAgeRestriction(String ageRestriction);

    List<String> findAllBooksTitlesByEditionTypeAndCopiesLessThan(EditionType editionType, int copies);

    List<String> findAllBooksWithGivenPrice(Integer lover_price, Integer higher_price);

    List<String> findAllBooksNotReleaseInGivenYear(String year);

    List<String> findAllReleaseDateBefore(String dateFromConsole);

    List<String> findAllTitleContainString(String contains);

    String findCountBookTitleLongerThan(int length);

    String bookTitleTypeRestrictionPrice(String title);

    Integer updateCopiesReleaseDateAfter(int copies, String dateFromConsole);

    Integer deleteBooksCopiesLoverThen(int copies);
    Integer selectBooksCountFromStoredProcedureByAuthorName(String name);
}