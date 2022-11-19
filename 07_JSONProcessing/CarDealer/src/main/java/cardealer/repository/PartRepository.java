package cardealer.repository;

import cardealer.domain.part.Part;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PartRepository extends JpaRepository<Part, Long> {

    @Query(value = "select * from `car-dealer`.parts order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Part> getRandomEntity();
}
