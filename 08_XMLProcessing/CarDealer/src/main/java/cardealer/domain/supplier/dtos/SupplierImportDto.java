package cardealer.domain.supplier.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "supplier")
@XmlAccessorType(XmlAccessType.FIELD)
public class SupplierImportDto {

    @XmlAttribute
    private String name;

    @XmlAttribute(name = "is-importer")
    private boolean isImporter;
}
