package IntegrationReports.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersCountInfo {

    private String organizationCode;
    private List<ShipperInfoDto> shipperInfoDto;
}
