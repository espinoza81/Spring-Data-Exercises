package cardealer.domain.car.dtos;

import cardealer.domain.part.dtos.PartDto;
import cardealer.domain.part.dtos.PartWithNameDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Set;
import java.util.stream.Collectors;

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

    public CarWithPartsDto carWithPartsDto(){

        CarWithoutPartsDto car = new CarWithoutPartsDto(make, model, travelledDistance);
        Set<PartWithNameDto> parts =
                this.parts
                        .stream()
                        .map(CarDto::partWithNameDto)
                        .collect(Collectors.toSet());

        return new CarWithPartsDto(car, parts);
    }

    public static PartWithNameDto partWithNameDto(PartDto partDto){
        return new PartWithNameDto(partDto.getName(), partDto.getPrice());
    }
}