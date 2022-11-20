package cardealer.repository;

import cardealer.domain.custumer.Customer;
import cardealer.domain.custumer.CustomerDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    @Query(value = "select * from `car-dealer`.customers order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Customer> getRandomEntity();

    Optional<List<Customer>> findAllByOrderByBirthDateAscYoungDriverDesc();
}
