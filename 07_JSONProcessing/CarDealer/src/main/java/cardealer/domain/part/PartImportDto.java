package cardealer.domain.part;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class PartImportDto {
    private String name;
    private BigDecimal price;
    private Integer quantity;
}
