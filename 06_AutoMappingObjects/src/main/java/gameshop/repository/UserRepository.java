package gameshop.repository;

import gameshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    int countByEmail(String email);
    Optional<User> findByEmailAndPassword(String email, String password);

}