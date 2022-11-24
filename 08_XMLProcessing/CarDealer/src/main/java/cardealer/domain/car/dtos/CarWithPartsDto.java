package cardealer.domain.car.dtos;

import cardealer.domain.part.dtos.PartWithNameDto;
import cardealer.domain.part.wrapper.PartWithNameWrapper;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "car")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarWithPartsDto {


    @XmlAttribute
    private String make;

    @XmlAttribute
    private String model;

    @XmlAttribute(name = "travelled-distance")
    private Long travelledDistance;

    @XmlElement(name = "parts")
    PartWithNameWrapper partWrapper;

}
