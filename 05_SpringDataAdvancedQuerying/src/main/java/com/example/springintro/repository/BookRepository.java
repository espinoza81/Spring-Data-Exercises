package com.example.springintro.repository;

import com.example.springintro.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Optional<List<Book>> findByAgeRestrictionEquals(AgeRestriction ageRestriction);

    Optional<List<Book>> findByEditionTypeEqualsAndCopiesLessThan(EditionType editionType, int copies);

    Optional<List<Book>> findByPriceBeforeOrPriceAfter(BigDecimal lover, BigDecimal higher);

    @Query("SELECT b.title FROM Book b WHERE substring(b.releaseDate, 1, 4) NOT LIKE :releaseYear")
    Optional<List<String>> findByReleaseDateYearNot(String releaseYear);

    Optional<List<Book>> findByReleaseDateBefore(LocalDate releaseDate);

    Optional<List<Book>> findByTitleContains(String contains);

    Optional<List<Book>> findByAuthorLastNameStartingWith(String startWith);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length ")
    Optional<Integer> countByTitleCharLengthLongerThan(int length);

    @Query("SELECT b.title AS title, b.editionType AS editionType, b.ageRestriction AS ageRestriction, b.price AS price "+
            "FROM Book b " +
            "WHERE b.title LIKE :title")
    Optional<BookTitleTypeRestrictionPrice> bookWithTitleTypeRestrictionPrice(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :releaseDate")
    Optional<Integer> updateCopiesReleaseDateAfter(int copies, LocalDate releaseDate);


    @Transactional
    Optional<Integer> deleteByCopiesLessThan(int copies);

    @Procedure("udp_books_written_by")
    int countBooksWrittenByAuthorStoredProcedure(String firstName, String lastName);
}