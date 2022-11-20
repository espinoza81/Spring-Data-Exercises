package cardealer.domain.car;

import cardealer.domain.part.PartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarDto {

    private String make;
    private String model;
    private Long travelledDistance;
    Set<PartDto> parts;

    public BigDecimal getCarPrice(){
        return parts
                .stream()
                .map(PartDto::getPrice)
                .reduce(BigDecimal.ONE, BigDecimal::add );
    }
}