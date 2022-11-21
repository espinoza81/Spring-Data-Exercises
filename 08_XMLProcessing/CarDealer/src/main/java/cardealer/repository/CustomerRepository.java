package cardealer.repository;

import cardealer.domain.custumer.Customer;
import cardealer.domain.custumer.CustomerDto;
import cardealer.domain.custumer.CustomerTotalSalesDto;
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

    @Query("SELECT new cardealer.domain.custumer.CustomerTotalSalesDto" +
            "(c.name, count(s), sum(p.price*(1.0-(s.discount/100.0)))) " +
            "FROM Customer c " +
            "JOIN c.sales s " +
            "JOIN s.car car " +
            "JOIN car.parts p " +
            "GROUP BY c " +
            "ORDER BY count(s) desc, sum(p.price*(1-s.discount/100)) desc")
//
    Optional<List<CustomerTotalSalesDto>> getAllCustomersWithTotalSale();
}
//*(1-s.discount/100)
//@Query("SELECT NEW com.manhattan.models.carDealer.dtos.CustomerSalesDto(c.name, COUNT(s), " +
//            "SUM(p.price * (1.0 - (s.discountPercentage/100.0)))) " +
//            "FROM Customer c JOIN c.sales s " +
//            "JOIN s.car car " +
//            "JOIN car.parts p " +
//            "GROUP BY c ")