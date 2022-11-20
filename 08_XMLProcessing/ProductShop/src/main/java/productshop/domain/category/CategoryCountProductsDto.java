package productshop.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CategoryCountProductsDto {
    private String category;
    private Long productsCount;
    private Double averagePrice;
    private BigDecimal totalRevenue;
}
