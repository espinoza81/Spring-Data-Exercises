package productshop.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import productshop.domain.BaseEntity;
import productshop.domain.product.Product;
import productshop.constant.OutputMessages;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.Objects;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(name = "first_name")
    private String firstName;

    @Size(min = 3, message = OutputMessages.LAST_NAME_TO_SHORT)
    @Column(name = "Last_name", nullable = false)
    private String lastName;

    @Column
    private Integer age;

    @ManyToMany
    @Fetch(FetchMode.JOIN)
    private Set<User> friend;

    @OneToMany(targetEntity = Product.class, mappedBy = "seller")
    @Fetch(FetchMode.JOIN)
    private Set<Product> soldProducts;

    @OneToMany(targetEntity = Product.class, mappedBy = "buyer")
    @Fetch(FetchMode.JOIN)
    private Set<Product> bought;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(firstName, user.firstName)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(getId(), user.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, getId());
    }
}