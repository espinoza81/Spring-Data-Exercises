package gameshop.service;

import gameshop.domain.game.Game;
import gameshop.domain.game.GameNameDTO;
import gameshop.domain.game.RegisterGameDTO;
import java.util.Optional;

public interface GameService {
    boolean gameWithTitleExist(String title);

    Game register(RegisterGameDTO registerGameDTO);

    Optional<Game> getByIdAndNotDeleted(Long id);

    String editGame(String[] input);


    GameNameDTO deleteGame(long id);

    String allGames();

    String detailsGame(String title);
}