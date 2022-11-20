package productshop.repository;

import org.springframework.data.jpa.repository.Query;
import productshop.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query(value = "select * from `product_shop`.users order by RAND() LIMIT 1", nativeQuery = true)
    Optional<User> getRandomEntity();

    Optional<List<User>> findAllByOrderByLastNameAscFirstNameAsc();
}