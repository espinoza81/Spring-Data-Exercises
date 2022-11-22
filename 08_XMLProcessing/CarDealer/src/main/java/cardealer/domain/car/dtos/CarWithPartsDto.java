package cardealer.domain.car.dtos;

import cardealer.domain.part.dtos.PartWithNameDto;
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


    private CarWithoutPartsDto car;

    @XmlElement
    public Set<PartWithNameDto> parts;
}
