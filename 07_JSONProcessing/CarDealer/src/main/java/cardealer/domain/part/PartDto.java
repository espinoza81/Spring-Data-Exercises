package cardealer.domain.part;

import cardealer.domain.supplier.SupplierDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartDto {
    private String name;
    private BigDecimal price;
    private Integer quantity;
    private SupplierDto supplier;
}
