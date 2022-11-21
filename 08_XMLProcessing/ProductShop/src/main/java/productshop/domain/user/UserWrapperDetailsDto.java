package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.ProductWrapperDto;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserWrapperDetailsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = " last-name")
    private String lastName;

    @XmlAttribute(name = "age")
    private Integer age;

    @XmlElement(name = "sold-products")
    private ProductWrapperDto soldProducts;

    public int soldProductsCount(){
        return soldProducts.getCount();
    }
}
