package usersystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import usersystem.models.Picture;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Integer> {

}