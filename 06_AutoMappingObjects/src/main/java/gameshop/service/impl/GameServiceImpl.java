package gameshop.service.impl;

import gameshop.core.GameValidator;
import gameshop.messages.OutputMessages;
import gameshop.model.entity.Game;
import gameshop.repository.GameRepository;
import gameshop.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public List<String> validateGamePart(String title, BigDecimal price, double sizeInMB, String youtubeId,
                                         String imageThumbnailURL, String description) {

        if(gameRepository.countByTitle(title) > 0){
            return List.of(OutputMessages.GAME_EXIST);
        }

        GameValidator gameValidator = new GameValidator();

        return gameValidator.isValid(title, price, sizeInMB, youtubeId, imageThumbnailURL, description);
    }

    @Override
    public void save(Game game) {
        gameRepository.save(game);
    }
}