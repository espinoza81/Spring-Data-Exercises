package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.ProductDto;
import productshop.domain.product.ProductImportDto;
import productshop.domain.product.ProductWrapperDto;

import java.util.List;
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
    private Set<ProductDto> soldProducts;
    private Set<ProductDto> bought;

    public String getFullName(){
        return firstName + " " + lastName;
    }

    public UsersCountWrapperDto usersCountWrapperDto(List<UserDto> users){
        List<UserWrapperDetailsDto> usersWrapper =
                users
                        .stream()
                        .map(UserDto::userWrapperDetailsDto)
                        .toList();

        return new UsersCountWrapperDto(usersWrapper);
    }

    public UserWrapperDetailsDto userWrapperDetailsDto(){
        List<ProductImportDto> products = soldProducts
                .stream()
                .map(ProductDto::ProductImportDto)
                .toList();
        ProductWrapperDto productWrapperDto = new ProductWrapperDto(products);
        return new UserWrapperDetailsDto(firstName, lastName, age, productWrapperDto);
    }
}