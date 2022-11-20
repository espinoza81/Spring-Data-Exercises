package productshop.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@NoArgsConstructor
@Getter
@Setter
public class UsersCountWrapperDto {
    private Integer usersCount;
    private List<UserWrapperDetailsDto> users;

    public UsersCountWrapperDto(List<UserWrapperDetailsDto> users) {
        this.users = users;
        this.usersCount = users.size();
    }
}