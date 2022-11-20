package cardealer.domain.car;

import cardealer.domain.BaseEntity;
import cardealer.domain.part.Part;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cars")
public class Car extends BaseEntity {
    @Column(nullable = false)
    private String make;

    @Column
    private String model;

    @Column(name = "travelled_distance", nullable = false)
    private Long travelledDistance;

    @ManyToMany
    Set<Part> parts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return Objects.equals(make, car.make)
                && Objects.equals(model, car.model)
                && Objects.equals(travelledDistance, car.travelledDistance)
                && Objects.equals(getId(), car.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, travelledDistance, getId());
    }
}