package IntegrationReports.model;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
public class IntegrationConfigMap {

    @Field("key")
    @NotEmpty
    private String key;

    @Field("value")
    @NotEmpty
    private String value;
}