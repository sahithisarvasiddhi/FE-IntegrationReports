package IntegrationReports.service;

import IntegrationReports.controller.ApiTrackReportsController;
import IntegrationReports.dto.InboundOrdersInfo;
import IntegrationReports.dto.OrdersCountInfo;
import IntegrationReports.dto.ShipperInfoDto;
import IntegrationReports.model.ApiRequestTrack;
import IntegrationReports.model.IntegrationConfigs;
import IntegrationReports.repository.ApiRequestTrackRepository;
import IntegrationReports.repository.IntegrationConfigsRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.*;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Service
public class ApiTrackReportsService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiTrackReportsController.class);

    @Autowired
    private ApiRequestTrackRepository apiRequestTrackRepository;

    @Autowired
    private IntegrationConfigsRepository integrationConfigsRepository;

    public ResponseEntity<?> getInboundOrdersCount() {
        InboundOrdersInfo inboundOrdersInfo = new InboundOrdersInfo();
        try {
            String currentDate = LocalDate.now().toString();
            ZonedDateTime[] dateTimes = getDateTimes(LocalDate.parse(currentDate));
            LOGGER.info("dateTimes:::" + dateTimes[0], dateTimes[1]);
            Map<String, List<String>> carrierAndShipperCodesList = getCarrierCodeAndShipperCodes();
            LOGGER.info("carrierAndShipperCodesList"+carrierAndShipperCodesList);
            if (carrierAndShipperCodesList != null) {
                for (Map.Entry<String, List<String>> entry : carrierAndShipperCodesList.entrySet()) {
                    OrdersCountInfo ordersCountInfo = new OrdersCountInfo();
                    LOGGER.info("entry:::" + entry);
                    getShippersList(entry, dateTimes, ordersCountInfo);
                    inboundOrdersInfo.getOrdersCountInfoList().add(ordersCountInfo);
                }
            }
        } catch (Exception ex) {
            LOGGER.info(String.valueOf(ex));
            return ResponseEntity.badRequest().body(String.valueOf(ex));
        }
        return ResponseEntity.ok(inboundOrdersInfo);
    }

    private void getShippersList(Map.Entry<String, List<String>> entry, ZonedDateTime[] dateTimes, OrdersCountInfo ordersCountInfo) {
        entry.getValue().forEach(eachCode -> {
            List<ApiRequestTrack> apiRequestTrackList = getApiRequestTracks(entry.getKey(), eachCode, dateTimes);
            LOGGER.info("apiTrackList :::" + apiRequestTrackList);
            if (!apiRequestTrackList.isEmpty()) {
                ShipperInfoDto shipperInfoDto = getOrderDetails(apiRequestTrackList);
                if(ordersCountInfo.getShipperInfoDto() == null)
                    ordersCountInfo.setShipperInfoDto(new ArrayList<>());
                ordersCountInfo.getShipperInfoDto().add(shipperInfoDto);
            } else {
                ShipperInfoDto shipperInfoDto = new ShipperInfoDto();
                shipperInfoDto.setShipperCode(eachCode);
                if(ordersCountInfo.getShipperInfoDto() == null)
                    ordersCountInfo.setShipperInfoDto(new ArrayList<>());
                ordersCountInfo.getShipperInfoDto().add(shipperInfoDto);
                ordersCountInfo.setOrganizationCode(entry.getKey());
            }
            ordersCountInfo.setOrganizationCode(entry.getKey());
            LOGGER.info("ordersCountInfo:::"+ ordersCountInfo);
        });
    }

    private List<ApiRequestTrack> getApiRequestTracks(String carrierCode, String eachCode, ZonedDateTime[] dateTimes) {
        List<ApiRequestTrack> apiRequestTrackList = apiRequestTrackRepository.findByShipperCodeAndCarrierCodeAndCreatedAtBetween(carrierCode, eachCode, dateTimes[0], dateTimes[1]);
        List<ApiRequestTrack> apiRequestTracks = apiRequestTrackList != null ? apiRequestTrackList : null;
        return apiRequestTracks;
    }

    private ShipperInfoDto getOrderDetails(List<ApiRequestTrack> apiRequestTrackList) {
        ShipperInfoDto shipperInfoDto = new ShipperInfoDto();
        List<ApiRequestTrack> processedOrdersList = new ArrayList<>();
        List<ApiRequestTrack> failedOrdersList = new ArrayList<>();
        for (ApiRequestTrack apiRequestTrack : apiRequestTrackList) {
            if (apiRequestTrack.getStatus().equalsIgnoreCase("COMPLETED")) {
                processedOrdersList.add(apiRequestTrack);
            } else if (apiRequestTrack.getStatus().equalsIgnoreCase("EXCEPTION")) {
                failedOrdersList.add(apiRequestTrack);
            }
        }
        shipperInfoDto.setShipperCode(apiRequestTrackList.get(0).getOrderDetails().getShipperCode());
        shipperInfoDto.setProcessedOrders(processedOrdersList.size());
        shipperInfoDto.setFailedOrders(failedOrdersList.size());
        shipperInfoDto.setActive(getActiveStatus(apiRequestTrackList));
        return shipperInfoDto;
    }

    private boolean getActiveStatus(List<ApiRequestTrack> apiRequestTrackList) {
        LocalDateTime getTime = LocalDateTime.now();
        LocalDateTime recentOrderDateTime = apiRequestTrackList.get(0).getCreatedAt();
        long hours = calculateHoursDifference(getTime, recentOrderDateTime);
        return hours < 2 ? true : false;
    }

    private static long calculateHoursDifference(LocalDateTime date1, LocalDateTime date2) {
        // Calculate the difference in hours
        return ChronoUnit.HOURS.between(date1, date2);
    }


    private ZonedDateTime[] getDateTimes(LocalDate currentDate) {
        try {
            ZonedDateTime startTime = currentDate.atStartOfDay().atZone(ZoneOffset.UTC);
            ZonedDateTime endTime = currentDate.atTime(23, 59, 59).atZone(ZoneOffset.UTC);
            return new ZonedDateTime[]{startTime, endTime};
        } catch (Exception e) {
            return null;
        }
    }

    private Map<String, List<String>> getCarrierCodeAndShipperCodes() {
        try {
            List<IntegrationConfigs> integrationConfigs = integrationConfigsRepository.findAll().stream().toList();
            List<IntegrationConfigs> filteredList = integrationConfigs.stream()
                    .filter(config -> config.getCarrierEDICode() != null).toList();
            Map<String, List<String>> keyValueMap = new HashMap<>();

            Map<String, List<IntegrationConfigs>> groupByCarrierCode = filteredList.stream().
                    collect(Collectors.groupingBy(IntegrationConfigs::getCarrierEDICode, toList()));

            for (Map.Entry<String, List<IntegrationConfigs>> entry : groupByCarrierCode.entrySet()) {
                List<String> shipperCodes = entry.getValue().stream().map(IntegrationConfigs::getShipperEDICode).distinct().collect(toList());
                keyValueMap.put(entry.getKey(), shipperCodes);
            }
            return keyValueMap;
        } catch (Exception e) {
            LOGGER.info(String.valueOf(e));
            return null;
        }
    }
}
