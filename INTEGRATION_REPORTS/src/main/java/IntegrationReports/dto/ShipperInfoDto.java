package IntegrationReports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShipperInfoDto {

    private String shipperCode;
    private int processedOrders;
    private int failedOrders;
    private boolean active = false;
}
