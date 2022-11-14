package gameshop.repository;

import gameshop.domain.game.GameNameDTO;
import gameshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

    @Transactional
    @Query("SELECT u.games FROM User u WHERE u.email = :email")
    Optional<Set<GameNameDTO>> findUserGames(String email);
}