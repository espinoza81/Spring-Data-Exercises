package cardealer.domain.custumer.dtos;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@XmlRootElement(name = "customer")
@XmlAccessorType(XmlAccessType.FIELD)
public class CustomerOrderBirthdateDto {

    @XmlElement
    private Long id;

    @XmlElement
    private String name;

    @XmlElement(name = "birth-date")
    private String birthDate;

    @XmlElement(name = "is-youn-drive")
    private boolean youngDriver;
}