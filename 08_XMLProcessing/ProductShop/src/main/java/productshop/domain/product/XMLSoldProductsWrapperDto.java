package productshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "sold-product")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLSoldProductsWrapperDto {

    @XmlElement(name = "product")
    private List<ProductWithBayerNameDto> products;
}
