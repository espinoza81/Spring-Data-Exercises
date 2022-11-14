package gameshop.repository;

import gameshop.domain.game.Game;
import gameshop.domain.game.GameDetails;
import gameshop.domain.game.GameNameDTO;
import gameshop.domain.game.GameNamePriceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    int countByTitle(String title);
    int countByTitleAndDeletedFalse(String title);
//    Optional<Game> findByTitleAndDeletedFalse(String title);

    Optional<Game> findByIdAndDeletedFalse(Long id);

    @Modifying
    @Transactional
    @Query("UPDATE Game g SET g.deleted = true WHERE g.id = :id AND g.deleted = false")
    int updateDeleteTrueById(Long id);

    @Query("SELECT g.title AS title FROM Game g WHERE g.id = :id")
    GameNameDTO selectTitleById(Long id);

    @Query("SELECT g.title AS title, g.price AS price FROM Game g")
    Set<GameNamePriceDTO> selectAllTitleAndPrice();

    Optional<GameDetails> findByTitle(String title);

    Set<Game> findByTitleIn(Collection<String> cart);
}