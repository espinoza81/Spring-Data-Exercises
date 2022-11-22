package cardealer.domain.part.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "part")
@XmlAccessorType(XmlAccessType.FIELD)
public class PartImportDto {

    @XmlAttribute
    private String name;

    @XmlAttribute
    private BigDecimal price;

    @XmlAttribute
    private Integer quantity;
}
