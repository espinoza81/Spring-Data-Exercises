package cardealer.repository;

import cardealer.domain.supplier.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {

    @Query(value = "select * from `car-dealer`.suppliers order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Supplier> getRandomEntity();
}
