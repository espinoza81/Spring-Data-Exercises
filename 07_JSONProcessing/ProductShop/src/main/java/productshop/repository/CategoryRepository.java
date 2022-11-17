package productshop.repository;

import org.springframework.data.jpa.repository.Query;
import productshop.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from `product_shop`.categories order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();
}