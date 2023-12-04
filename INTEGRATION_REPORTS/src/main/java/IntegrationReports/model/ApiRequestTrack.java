package IntegrationReports.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;
import java.util.Map;

@EqualsAndHashCode(callSuper = true)
@Document(collection = "order_api_request_tracks")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiRequestTrack extends BaseEntity {

    @Field("order_details")
    private OrderDetails orderDetails;

    private String status;

    @Field("error_messages")
    private List<String> errorMessages;

    @Field("validation_error_fields")
    private List<Map<String, String>> validationErrorFields;

    @Field("customer_order_id")
    private String customerOrderId;

    @Field("user_name")
    private String userName;

    @Field("user_id")
    private String userId;

}
