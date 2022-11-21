package cardealer.domain.part;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PartWithNameDto {
    @SerializedName("Name")
    private String name;

    @SerializedName("Price")
    private BigDecimal price;
}
