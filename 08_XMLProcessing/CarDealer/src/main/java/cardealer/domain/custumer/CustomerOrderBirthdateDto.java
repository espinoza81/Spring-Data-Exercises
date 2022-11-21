package cardealer.domain.custumer;

import cardealer.domain.sale.Sale;
import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class CustomerOrderBirthdateDto {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private String birthDate;

    @SerializedName("IsYoungDriver")
    private boolean youngDriver;

    @SerializedName("Sales")
    private Sale[] sales;

    public CustomerOrderBirthdateDto() {
        this.sales = new Sale[0];
    }

    public CustomerOrderBirthdateDto(Long id, String name, String birthDate, boolean youngDriver) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
        this.youngDriver = youngDriver;
        this.sales = new Sale[0];
    }

    public void setSales(Sale[] sales) {
        this.sales = new Sale[0];
    }
}
