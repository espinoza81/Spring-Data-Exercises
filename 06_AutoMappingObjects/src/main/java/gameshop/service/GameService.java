package gameshop.service;

import gameshop.model.entity.Game;

import java.math.BigDecimal;
import java.util.List;

public interface GameService {

    List<String> validateGamePart(String title, BigDecimal price, double sizeInMB,
        String youtubeId, String imageThumbnailURL, String description);

    void save(Game game);
}