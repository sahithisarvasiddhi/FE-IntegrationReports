package IntegrationReports.model;

import IntegrationReports.constants.IntegrationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Document(collection = "account_io_configs")
@Data
@AllArgsConstructor
public class IntegrationConfigs extends BaseEntity {

    @Field("organization_id")
    private String organizationId;

    @Field("send_status_updates")
    private Boolean sendStatusUpdates = false;

    @Field("integration_code")
    private String integrationCode;

    @Field("account_id")
    private String accountId;

    @Field("status_code")
    private List<String> statusCodes;

    @Field("exception_code")
    private List<String> exceptionCodes;

    @Field("shipper_edi_code")
    private String shipperEDICode;

    @Field("carrier_edi_code")
    private String carrierEDICode;

    @Field("integration_type")
    private IntegrationType integrationType;

    @Field("inbound_settings")
    private List<IntegrationConfigMap> inboundSettings;

    @Field("outbound_settings")
    private List<IntegrationConfigMap> outboundSettings;

}