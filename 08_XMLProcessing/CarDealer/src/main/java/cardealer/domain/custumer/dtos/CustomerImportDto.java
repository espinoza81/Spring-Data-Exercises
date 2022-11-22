package cardealer.domain.custumer.dtos;

import cardealer.config.LocalDateTimeAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerImportDto {

    @XmlAttribute
    private String name;

    @XmlElement(name = "birth-date")
    @XmlJavaTypeAdapter(LocalDateTimeAdapter.class)
    private LocalDateTime birthDate;

    @XmlElement(name = "is-young-driver")
    private boolean isYoungDriver;
}