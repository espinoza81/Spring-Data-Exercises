package cardealer.domain.car.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarToyotaDto {

    @XmlAttribute
    private Long id;

    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;
}
