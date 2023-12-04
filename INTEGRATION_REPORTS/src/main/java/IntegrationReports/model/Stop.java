package IntegrationReports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Stop {

    @JsonProperty("stop_num")
    @Field("stop_num")
    @Schema(description = "Stop Number")
    private int stopNum; //mandatory

    @JsonProperty("reason")
    @NotEmpty(message = "reason not found")
    private String reason;

    @JsonProperty("reason_desc")
    @Field("reason_desc")
    private String reasonDesc;

    @JsonProperty("weight")
    @Schema(description = "Total weight of all the items for this stop.")
    private double weight;

    @JsonProperty("volume")
    @Schema(description = "Total volume of all the items for this stop.")
    private double volume;

    @JsonProperty("num_items")
    @Field("num_items")
    @Schema(description = "Quantity for the stop.")
    private int numItems;

    @JsonProperty("uom")
    @Schema(description = "Unit of Measure")
    private String uom;

    @JsonProperty("description")
    private String description;

    @JsonProperty("requested_date")
    @Field("requested_date")
    @Schema(description = "Appointment Start Date and time of the order.")
    private String requestedDate;

    @JsonProperty("date1_timezone")
    @Field("date1_timezone")
    @Schema(description = "Time Zone for Appointment Start Date and time. ")
    private String date1TimeZone;

    @JsonProperty("latest_requested_date")
    @Field("latest_requested_date")
    @Schema(description = "Appointment End date and time of the order.")
    private String latestRequestedDate;

    @JsonProperty("date2_timezone")
    @Field("date2_timezone")
    @Schema(description = "Time Zone for Appointment Start Date and time.")
    private String date2TimeZone;

    @JsonProperty("pallets")
    private int pallets;

    @JsonProperty("name")
    @NotEmpty(message = "name not found")
    @Schema(description = "Name of the Location")
    private String name;

    @JsonProperty("address1")
    @Schema(description = "Line 1 of the address for the stop.")
    private String address1;

    @JsonProperty("address2")
    @Schema(description = "Line 2 of the address for the stop.")
    private String address2;

    @JsonProperty("city")
    @Schema(description = "Stop address city")
    private String city;

    @JsonProperty("state")
    @Schema(description = "Stop address state")
    private String state;

    @JsonProperty("zip")
    @Schema(description = "Stop address Zipcode")
    private String zip;

    @JsonProperty("country")
    @Schema(description = "Stop address Country")
    private String country;

    @JsonProperty("type")
    private String type;

    @JsonProperty("code")
    private String code;

    @JsonProperty("contact")
    private Contact contact;

    @JsonProperty(value = "level_of_service")
    @Field("level_of_service")
    @Schema(description = "Level of Service for the order at the stop")
    private String levelOfService = "DEFAULT";

    @JsonProperty(value = "service_duration")
    @Field("service_duration")
    @Schema(description = "Duration of the service at the stop")
    private int serviceDuration;

    @JsonProperty("note")
    private String note;

    @JsonProperty("references")
    private List<Reference> references;

    @JsonProperty("items")
    @Field("items")
    @Schema(description = "List of Items")
    private List<Item> itemList;
}
