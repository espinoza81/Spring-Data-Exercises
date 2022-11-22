package cardealer.domain.car.wrapper;

import cardealer.domain.car.dtos.CarWithPartsDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarPartsWrapper {

    @XmlElement(name = "car")
    private List<CarWithPartsDto> cars;
}
