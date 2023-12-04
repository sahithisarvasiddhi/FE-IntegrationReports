package IntegrationReports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @JsonProperty("reference")
    private String reference;

    @JsonProperty("purchase_order_number")
    @Field("purchase_order_number")
    private String purchaseOrderNumber;

    @JsonProperty("weight_qualifier")
    @Field("weight_qualifier")
    private String weightQualifier;

    @JsonProperty("weight")
    @Schema(description = "Weight of the item")
    private int weight;

    @JsonProperty("quantity")
    @Schema(description = "Quantity")
    private int quantity;

    @JsonProperty("uom")
    @Schema(description = "Unit of Measure")
    private String uom;

    @JsonProperty("volume_qualifier")
    @Field("volume_qualifier")
    private String volumeQualifier;

    @JsonProperty("length")
    @Schema(description = "Length of the item")
    private double length;

    @JsonProperty("width")
    @Schema(description = "Width of the item")
    private double width;

    @JsonProperty("height")
    @Schema(description = "Height of the item")
    private double height;

    @JsonProperty("volume")
    @Schema(description = "Volume of the item")
    private String volume;

    @JsonProperty("description")
    private String description;

    @Transient
    private String dimUOM;
}
