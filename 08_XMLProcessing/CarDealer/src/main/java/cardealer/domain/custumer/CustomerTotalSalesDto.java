package cardealer.domain.custumer;

import cardealer.config.SpentMoneyAdapter;
import com.google.gson.annotations.JsonAdapter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CustomerTotalSalesDto {
    private String fullName;
    private Long boughtCars;

    @JsonAdapter(SpentMoneyAdapter.class)
    private double spentMoney;
}
