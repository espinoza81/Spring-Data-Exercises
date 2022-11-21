package productshop.domain.product;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "sold-products")
@XmlAccessorType(XmlAccessType.FIELD)
public class ProductWrapperDto {

    @XmlAttribute(name = "count")
    private Integer count;

    @XmlElement(name = "product")
    private List<XMLProductNamePriceAttributeDto> products;

    public ProductWrapperDto(List<XMLProductNamePriceAttributeDto> products) {
        this.products = products;
        this.count = products.size();
    }
}