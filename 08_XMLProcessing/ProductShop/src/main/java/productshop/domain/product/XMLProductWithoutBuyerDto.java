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

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "products")
@XmlAccessorType(XmlAccessType.FIELD)
public class XMLProductWithoutBuyerDto {

    @XmlElement(name = "product")
    private List<XMLProductWithBayerNameDto> products;
}
