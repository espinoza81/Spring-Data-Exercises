package productshop.repository;

import productshop.domain.product.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productshop.domain.product.ProductDto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<List<Product>> findByBuyerNullAndPriceBetweenOrderByPrice(BigDecimal start, BigDecimal end);
}