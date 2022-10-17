package E05_BillsPaymentSystem;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "_05_billing_details")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class BillingDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String number;

    @ManyToMany(mappedBy = "billingDetails")
    private Set<User> users;

    public BillingDetail() {
    }

    public BillingDetail(String number, Set<User> users) {
        this.number = number;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}
