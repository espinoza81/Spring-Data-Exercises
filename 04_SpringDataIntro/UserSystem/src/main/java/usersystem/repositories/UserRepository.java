package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import usersystem.models.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> findByEmailEndingWith(String provider);

    List<User> findByLastTimeLoggedInIsBefore(LocalDate lastLoggedInBefore);

    @Transactional
    void deleteAllByForDeletion(boolean isDeleted);
}