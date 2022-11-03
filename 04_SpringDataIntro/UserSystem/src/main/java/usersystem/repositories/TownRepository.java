package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.models.Town;

@Repository
public interface TownRepository extends JpaRepository<Town, Integer> {
    Town getByName(String name);
}