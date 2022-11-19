package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.ProductWithBayerNameDto;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserSoldProductsDto {
    private String firstName;
    private String lastName;
    private Set<ProductWithBayerNameDto> soldProducts;
}