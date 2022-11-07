package com.example.springintro.repository;

import com.example.springintro.model.entity.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByAgeRestrictionEquals(AgeRestriction ageRestriction);

    List<Book> findByEditionTypeEqualsAndCopiesLessThan(EditionType editionType, int copies);

    List<Book> findByPriceBeforeOrPriceAfter(BigDecimal lover, BigDecimal higher);

    @Query("SELECT b.title FROM Book b WHERE substring(b.releaseDate, 1, 4) NOT LIKE :releaseYear")
    List<String> findByReleaseDateYearNot(String releaseYear);

    List<Book> findByReleaseDateBefore(LocalDate releaseDate);

    List<Book> findByTitleContains(String contains);

    List<Book> findByAuthorLastNameStartingWith(String startWith);

    @Query("SELECT COUNT(b) FROM Book b WHERE length(b.title) > :length ")
    Integer countByTitleCharLengthLongerThan(int length);

    @Query("SELECT b.title AS title, b.editionType AS editionType, b.ageRestriction AS ageRestriction, b.price AS price "+
            "FROM Book b " +
            "WHERE b.title LIKE :title")
    BookTitleTypeRestrictionPrice bookWithTitleTypeRestrictionPrice(String title);

    @Modifying
    @Transactional
    @Query("UPDATE Book b SET b.copies = b.copies + :copies WHERE b.releaseDate > :releaseDate")
    Integer updateCopiesReleaseDateAfter(int copies, LocalDate releaseDate);


    @Transactional
    Integer deleteByCopiesLessThan(int copies);

//   @Procedure(procedureName = "udp_books_written_by")
           //, outputParameterName = "copies")
    @Query(value = "{call udp_books_written_by(:first_n, :last_n)}", nativeQuery = true)
    Integer udpBooksWrittenBy(@Param("first_n") String firstName, @Param("last_n") String lastName);
}