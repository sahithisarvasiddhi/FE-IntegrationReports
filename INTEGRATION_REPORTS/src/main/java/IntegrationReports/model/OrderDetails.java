package IntegrationReports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetails {

    @JsonProperty("transaction_id")
    @Field("transaction_id")
    @Schema(description = "Transaction ID for the order", requiredMode = Schema.RequiredMode.REQUIRED)
    private int transactionId; //mandatory

    @JsonProperty("customer_identifier")
    @NotEmpty(message = "customer_identifier not found")
    @Field("customer_identifier")
    @Schema(description = "Customer Identifier of the order")
    private String customerIdentifier;

    @JsonProperty("shipper_code")
    @NotEmpty(message = "shipper_code not found")
    @Schema(description = "Shipper code of the order")
    @Field("shipper_code")
    private String shipperCode;

    @JsonProperty("carrier_code")
    @NotEmpty(message = "carrier_code not found")
    @Schema(description = "Carrier code of the order")
    @Field("carrier_code")
    private String carrierCode;

    @JsonProperty("purpose_code")
    @NotEmpty(message = "purpose_code not found")
    @Field("purpose_code")
    private String purposeCode;

    @JsonProperty("purpose_description")
    @NotEmpty(message = "purpose_description not found")
    @Field("purpose_description")
    private String purposeDescription;

    @JsonProperty("shipment_id")
    @NotEmpty(message = "shipment_id not found")
    @Field("shipment_id")
    @Schema(description = "Unique Identifier for the order.")
    private String shipmentId;

    @JsonProperty("equipment")
    private String equipment;

    @JsonProperty("equipment_number")
    @Field("equipment_number")
    private String equipmentNumber;

    @JsonProperty("total_pieces_of_equipment")
    @Field("total_pieces_of_equipment")
    private int totalPiecesOfEquipment;

    @JsonProperty("equip_length")
    @Field("equip_length")
    private String equipLength;

    @JsonProperty("pallets")
    private int pallets;

    @JsonProperty("weight")
    @Schema(description = "Total weight of the items in this order.")
    private double weight;

    @JsonProperty("length")
    private double length;

    @JsonProperty("width")
    private double width;

    @JsonProperty("height")
    private double height;

    @JsonProperty("weight_unit")
    @Field("weight_unit")
    @Schema(description = "Unit of weight.")
    private String weightUnit;

    @JsonProperty("volume")
    @Schema(description = "Volume of the items in this order.")
    private int volume;

    @JsonProperty("payment_method")
    @Field("payment_method")
    private String paymentMethod;

    @JsonProperty("is_hazardous")
    @Field("is_hazardous")
    private boolean isHazardous;

    @JsonProperty("references")
    private List<Reference> references;

    @JsonProperty("note")
    private String note;

    @Valid
    @JsonProperty("stops")
    @NotEmpty(message = "stops not found")
    @Schema(description = "Stops for this order.")
    private List<Stop> stops;

    @JsonProperty("bill_to")
    @Field("bill_to")
    private BillTo billTo;

    @Field("source_from")
    @Schema(hidden = true)
    private String sourceFrom;

    @JsonProperty("instructions")
    private String instructions;

    @JsonProperty("miscellaneous")
    private String miscellaneous;

    @Field("special_handling_code")
    @JsonProperty("special_handling_code")
    @Schema(description = "The service type of the shipment, such as PUC, DEL, and PUD.")
    private String specialHandlingCode;

    @Field("special_services_code")
    @JsonProperty("special_services_code")
    @Schema(description = "Special service code")
    private String specialServicesCode;

    @Field("special_handling_desc")
    @JsonProperty("special_handling_desc")
    @Schema(description = "Special handling description.")
    private String specialHandlingDesc;
}