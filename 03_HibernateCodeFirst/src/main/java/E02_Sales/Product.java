package E02_Sales;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "_02_products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double quantity;
    @Column(nullable = false)
    private BigDecimal price;
    @OneToMany(targetEntity = Sale.class, mappedBy = "product")
    private Set<Sale> sales;


    public Product() {
    }

    public Product(String name, double quantity, BigDecimal price) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}