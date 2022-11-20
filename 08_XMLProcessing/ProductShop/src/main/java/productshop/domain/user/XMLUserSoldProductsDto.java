package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import productshop.domain.product.XMLSoldProductsWrapperDto;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLUserSoldProductsDto {

    @XmlAttribute(name = "first-name")
    private String firstName;

    @XmlAttribute(name = "last-name")
    private String lastName;

    @XmlElement(name = "sold-product")
    private XMLSoldProductsWrapperDto soldProducts;
}
