package com.example.springintro.model.entity;

public interface AuthorNameWithTotalCopies {
    String FORMAT_AUTHORS_WITH_TOTAL_COPIES = "%s %s have %d total copies released";
    String getFirstName();
    String getLastName();
    long getTotalCopies();

    default String info() {
        return String.format(FORMAT_AUTHORS_WITH_TOTAL_COPIES, getFirstName(), getLastName(), getTotalCopies());
    }
}