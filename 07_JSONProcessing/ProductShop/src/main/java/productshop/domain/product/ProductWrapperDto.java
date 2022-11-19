package productshop.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class ProductWrapperDto {
    private Integer count;
    private List<ProductImportDto> products;

    public ProductWrapperDto(List<ProductImportDto> products) {
        this.products = products;
        this.count = products.size();
    }
}