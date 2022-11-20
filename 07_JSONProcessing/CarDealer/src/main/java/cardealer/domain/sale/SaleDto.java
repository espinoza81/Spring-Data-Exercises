package cardealer.domain.sale;

import cardealer.domain.car.CarDto;
import cardealer.domain.custumer.CustomerDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class SaleDto {
    private Integer discount;
    private CarDto car;
    private CustomerDto customer;
}
