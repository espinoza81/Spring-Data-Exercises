package productshop.repository;

import org.springframework.data.jpa.repository.Query;
import productshop.domain.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productshop.domain.category.CategoryCountProductsDto;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    @Query(value = "select * from `product_shop`.categories order by RAND() LIMIT 1", nativeQuery = true)
    Optional<Category> getRandomEntity();

    @Query("select new productshop.domain.category.CategoryCountProductsDto(" +
            "c.name, count(p.id), avg(p.price), sum(p.price)) " +
            "FROM Product p " +
            "JOIN p.categories c " +
            "GROUP BY c.id " +
            "ORDER BY count(p.id) DESC")
    Optional<List<CategoryCountProductsDto>> getCategoriesSummary();
}