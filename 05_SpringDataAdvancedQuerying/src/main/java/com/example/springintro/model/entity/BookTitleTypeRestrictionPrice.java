package com.example.springintro.model.entity;

import java.math.BigDecimal;

public interface BookTitleTypeRestrictionPrice {
    String BOOKS_INFO = "Title: %s%nEdition type: %s%nAge restriction: %s%nPrice: %.2f $";
    String getTitle();
    EditionType getEditionType();
    AgeRestriction getAgeRestriction();
    BigDecimal getPrice();

    default String info() {
        return String.format(BOOKS_INFO, getTitle(), getEditionType(), getAgeRestriction(), getPrice());
    }
}