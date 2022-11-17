package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.ProductDto;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDto {

    private String firstName;
    private String lastName;
    private Integer age;
    private Set<UserDto> friend;
    private Set<ProductDto> sold;
    private Set<ProductDto> bought;

    public String getFullName(){
        return firstName + " " + lastName;
    }
}
