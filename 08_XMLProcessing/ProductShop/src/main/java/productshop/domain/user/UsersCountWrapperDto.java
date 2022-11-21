package productshop.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.xml.bind.annotation.*;
import java.util.List;

@NoArgsConstructor
@Getter
@Setter
@XmlRootElement(name = "users")
@XmlAccessorType(XmlAccessType.FIELD)
public class UsersCountWrapperDto {

    @XmlAttribute(name = "count")
    private Integer usersCount;

    @XmlElement(name = "user")
    private List<UserWrapperDetailsDto> users;

    public UsersCountWrapperDto(List<UserWrapperDetailsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}