package gameshop.domain.game;

import java.time.LocalDate;

public interface GameDetails extends GameNamePriceDTO{
    String GAME_INFO = "Title: %s%n" +
            "Price: %.2f%n" +
            "Description: %s%n" +
            "Release date: %s";
    String getDescription();
    LocalDate getReleaseDate();

    default String info() {
        return String.format(GAME_INFO, getTitle(), getPrice(), getDescription(), getReleaseDate());
    }
}