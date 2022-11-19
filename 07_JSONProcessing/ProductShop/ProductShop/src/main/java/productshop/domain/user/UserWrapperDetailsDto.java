package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.ProductWrapperDto;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserWrapperDetailsDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private ProductWrapperDto soldProducts;

    public int soldProductsCount(){
        return soldProducts.getCount();
    }
}
