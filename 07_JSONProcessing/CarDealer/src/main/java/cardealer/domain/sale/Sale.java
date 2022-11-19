package cardealer.domain.sale;

import cardealer.domain.BaseEntity;
import cardealer.domain.car.Car;
import cardealer.domain.custumer.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "sales")
public class Sale extends BaseEntity {

    @Column
    @Min(value = 0)
    @Max(value = 100)
    private Integer discount;

    @OneToOne
    private Car car;

    @ManyToOne(optional = false)
    private Customer customer;
}
