package IntegrationReports.controller;

import IntegrationReports.service.ApiTrackReportsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
public class ApiTrackReportsController {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiTrackReportsController.class);

    @Autowired
    private ApiTrackReportsService apiTrackReportsService;



    @GetMapping("/inbound_orders_count")
//    @Scheduled(fixedRate = 2 * 60 * 60 * 1000) // Run every 2 hours
    public ResponseEntity<?> inboundOrdersCount(){
        LOGGER.info("*** In InboundOrdersCount Controller ***");
        return apiTrackReportsService.getInboundOrdersCount();
    }

}
