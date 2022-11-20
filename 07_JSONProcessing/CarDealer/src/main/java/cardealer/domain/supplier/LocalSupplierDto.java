package cardealer.domain.supplier;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LocalSupplierDto {
    @SerializedName("Id")
    private Long id;

    @SerializedName("Name")
    private String name;

    private Long partsCount;
}