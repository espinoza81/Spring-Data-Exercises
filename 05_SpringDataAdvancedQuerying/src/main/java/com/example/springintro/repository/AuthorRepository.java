package com.example.springintro.repository;

import com.example.springintro.model.entity.Author;
import com.example.springintro.model.entity.AuthorNameWithTotalCopies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findByFirstNameEndsWith(String endWith);

    List<Author> findByLastNameStartsWith(String startWith);

    @Query("SELECT a.firstName AS firstName, a.lastName AS lastName, SUM(b.copies) AS totalCopies "+
            "FROM Author a " +
            "JOIN a.books AS b " +
            "GROUP BY b.author " +
            "ORDER BY totalCopies DESC")
    List<AuthorNameWithTotalCopies> authorsByBookCopies();
}