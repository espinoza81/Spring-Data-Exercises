package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.models.Album;

@Repository
public interface AlbumRepository extends JpaRepository<Album, Integer> {

}