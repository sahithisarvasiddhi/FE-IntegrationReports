package IntegrationReports.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Reference {

    @JsonProperty("identifier")
    @Schema(description = "Reference Type identifier.")
    private String identifier;

    @JsonProperty("qualifier")
    @Schema(description = "Reference")
    private String qualifier;

    @JsonProperty("qual_desc")
    @Field("qual_desc")
    @Schema(description = "Description og the reference")
    private String qualDesc;
}
