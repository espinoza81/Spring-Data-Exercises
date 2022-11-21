package cardealer.domain.car;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CarWithoutPartsDto {
    @SerializedName("Make")
    private String make;

    @SerializedName("Model")
    private String model;

    @SerializedName("TravelledDistance")
    private Long travelledDistance;
}
