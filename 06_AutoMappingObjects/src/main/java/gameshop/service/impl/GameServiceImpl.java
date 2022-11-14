package gameshop.service.impl;

import gameshop.domain.game.*;
import gameshop.exceptions.ValidationException;
import gameshop.messages.OutputMessages;
import gameshop.repository.GameRepository;
import gameshop.service.GameService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {

    private final GameRepository gameRepository;
    private final ModelMapper mapper;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository, ModelMapper mapper) {
        this.gameRepository = gameRepository;
        this.mapper = mapper;
    }

    @Override
    public boolean gameWithTitleExist(String title) {
        return gameRepository.countByTitle(title) > 0;
    }

    @Override
    public Game register(RegisterGameDTO registerGameDTO) {

        Game toRegister = mapper.map(registerGameDTO, Game.class);

        return this.gameRepository.save(toRegister);
    }

    @Override
    public Optional<Game> getByIdAndNotDeleted(Long id) {
        return gameRepository.findByIdAndDeletedFalse(id);
    }

    @Override
    public String editGame(String[] input) {
        Long id = Long.parseLong(input[1]);

        Optional<Game> gameToEdit = gameRepository.findByIdAndDeletedFalse(id);

        if (gameToEdit.isEmpty()) {
            throw new ValidationException(String.format(OutputMessages.GAME_NOT_EXIST, id));
        }

        Game game = gameToEdit.get();

        RegisterGameDTO updateGameDTO = new RegisterGameDTO(input);

        updateGameDTO.validate();

        if(gameRepository.countByTitle(updateGameDTO.getTitle()) > 0){
            throw new ValidationException(OutputMessages.GAME_EXIST);
        }

        game.update(updateGameDTO);

        gameRepository.save(game);

        return game.getTitle();
    }

    @Override
    public GameNameDTO deleteGame(long id) {
        if(gameRepository.updateDeleteTrueById(id) != 1){
            throw new ValidationException(OutputMessages.GAME_NOT_EXIST);
        }

        return gameRepository.selectTitleById(id);
    }

    @Override
    public String allGames() {
        return gameRepository.selectAllTitleAndPrice()
                .stream()
                .map(GameNamePriceDTO::info)
                .collect(Collectors.joining(System.lineSeparator()));
    }

    @Override
    public String detailsGame(String title) {
        Optional<GameDetails> gameDetails = gameRepository.findByTitle(title);

        if(gameDetails.isEmpty()){
            throw new ValidationException(OutputMessages.GAME_WITH_TITLE_NOT_EXIST);
        }

        return gameDetails.get().info();
    }
}