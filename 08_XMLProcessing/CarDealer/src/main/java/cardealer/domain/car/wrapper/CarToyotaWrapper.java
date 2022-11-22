package cardealer.domain.car.wrapper;

import cardealer.domain.car.dtos.CarToyotaDto;
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
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "cars")
@XmlAccessorType(XmlAccessType.FIELD)
public class CarToyotaWrapper {

    @XmlElement(name = "car")
    List<CarToyotaDto> cars;
}
