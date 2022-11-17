package productshop.domain.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import productshop.domain.BaseEntity;
import productshop.domain.category.Category;
import productshop.domain.user.User;
import productshop.constant.OutputMessages;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

    @Size(min = 3, message = OutputMessages.NAME_TOO_SHORT)
    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    @Fetch(FetchMode.JOIN)
    private User buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id", nullable = false)
    @Fetch(FetchMode.JOIN)
    private User seller;

    @Fetch(FetchMode.JOIN)
    @ManyToMany
            //(targetEntity = Category.class, mappedBy = "products")
    Set<Category> categories;
}