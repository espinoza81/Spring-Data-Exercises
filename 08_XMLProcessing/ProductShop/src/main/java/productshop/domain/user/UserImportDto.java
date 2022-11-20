package productshop.domain.user;

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
@XmlRootElement(name = "user")
@XmlAccessorType(XmlAccessType.FIELD)
public class UserImportDto {

        @XmlAttribute(name = "first-name")
        private String firstName;

        @XmlAttribute(name = "last-name")
        private String lastName;

        @XmlAttribute
        private Integer age;
}
//<user first-name="Eugene" last-name="Stewart" age="65"/>