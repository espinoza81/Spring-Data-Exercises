package E02_Sales;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "_02_store_locations")
public class StoreLocation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "location_name", nullable = false)
    private String locationName;
    @OneToMany(mappedBy = "storeLocation")
    private Set<Sale> sales;

    public StoreLocation() {
    }

    public StoreLocation(String locationName) {
        this.locationName = locationName;
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

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }
}
