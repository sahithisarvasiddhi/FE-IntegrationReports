package IntegrationReports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @JsonProperty("name1")
    @Schema(description = "Name/Firstname of the Consignee/Carrier")
    private String name1;

    @JsonProperty("phone1")
    @Schema(description = "Phone Number of the Consignee/Carrier")
    private String phone1;

    @JsonProperty("email1")
    @Schema(description = "Email of the Consignee/Carrier")
    private String email1;

    @JsonProperty("name2")
    @Schema(description = "Other name/Lastname of the Consignee/Carrier")
    private String name2;

    @JsonProperty("phone2")
    @Schema(description = "Alternate Phone Number of the Consignee/Carrier")
    private String phone2;

    @JsonProperty("email2")
    @Schema(description = "Alternate Email of the Consignee/Carrier")
    private String email2;
}
