package E02_Sales;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "_02_customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    private String email;
    @Column(name = "credit_card_number", nullable = false)
    private String creditCardNumber;
    @OneToMany(mappedBy = "customer")
    private Set<Sale> sales;

    public Customer() {
    }

    public Customer(String name, String email, String creditCardNumber) {
        this.name = name;
        this.email = email;
        this.creditCardNumber = creditCardNumber;
        this.sales = new HashSet<>();
    }

    public void addSales(Sale sale){
        this.sales.add(sale);
    }

    public void removeSales(Sale sale){
        this.sales.remove(sale);
    }

    public Set<Sale> getSales() {
        return Collections.unmodifiableSet(sales);
    }

    //public void setSales(Set<Sale> sales) {
    //    this.sales = sales;
    //}


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }
}