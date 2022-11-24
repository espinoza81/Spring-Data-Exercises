package cardealer.domain.sale.dtos;

import cardealer.domain.car.dtos.CarWithoutPartsDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "sale")
@XmlAccessorType(XmlAccessType.FIELD)
public class SaleWithDiscountDto {

    @XmlElement
    public CarWithoutPartsDto car;

    @XmlElement(name = "customer-name")
    public String customerName;

    @XmlElement
    @SerializedName("Discount")
    public  double discount;

    @XmlElement
    public BigDecimal price;

    @XmlElement(name = "price-with-discount")
    public BigDecimal priceWithDiscount;
}
