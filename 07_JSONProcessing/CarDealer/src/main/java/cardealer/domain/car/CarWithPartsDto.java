package cardealer.domain.car;

import cardealer.domain.part.PartWithNameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CarWithPartsDto {
    private CarWithoutPartsDto car;
    public Set<PartWithNameDto> parts;
}
