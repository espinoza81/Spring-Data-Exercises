package cardealer.domain.supplier;

import cardealer.domain.part.PartDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SupplierDto {
    private String name;
    private boolean isImporter;
    Set<PartDto> parts;
}
