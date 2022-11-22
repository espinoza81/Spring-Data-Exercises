package cardealer.domain.custumer;

import cardealer.domain.sale.SaleDto;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@AllArgsConstructor
public class CustomerDto {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    @SerializedName("BirthDate")
    private LocalDateTime birthDate;

    @SerializedName("IsYoungDriver")
    private boolean youngDriver;

    @SerializedName("Sales")
    private Set<SaleDto> sales;
}
