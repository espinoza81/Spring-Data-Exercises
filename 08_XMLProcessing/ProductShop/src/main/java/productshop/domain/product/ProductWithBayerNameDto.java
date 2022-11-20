package productshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductWithBayerNameDto {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String buyerLastName;
}
