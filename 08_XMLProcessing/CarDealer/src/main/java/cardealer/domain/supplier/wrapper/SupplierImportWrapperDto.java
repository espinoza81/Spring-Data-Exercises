package cardealer.domain.supplier.wrapper;

import cardealer.domain.supplier.dtos.SupplierImportDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "suppliers")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportWrapperDto {

    @XmlElement(name = "supplier")
    List<SupplierImportDto> suppliers;
}
