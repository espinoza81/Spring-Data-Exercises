package productshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.category.CategoryDto;
import productshop.domain.user.UserDto;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProductDto {
    private String name;
    private BigDecimal price;
    private UserDto buyer;
    private UserDto seller;
    Set<CategoryDto> categories;

    public ProductWithoutBuyerDto productWithoutBuyerDto(){
        return new ProductWithoutBuyerDto(name, price, seller.getFullName());
    }

    public XMLProductWithBayerNameDto xmlProductWithBuyerDto(){
        return new XMLProductWithBayerNameDto(name, price, seller.getFullName());
    }

    public ProductImportDto ProductImportDto(){
        return new ProductImportDto(name, price);
    }
}
