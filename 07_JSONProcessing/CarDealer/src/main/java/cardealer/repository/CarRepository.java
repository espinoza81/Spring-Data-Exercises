package cardealer.repository;

import cardealer.domain.car.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query(value = "select * from `car-dealer`.cars order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Car> getRandomEntity();
}
