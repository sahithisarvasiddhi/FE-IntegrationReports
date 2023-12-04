package IntegrationReports.repository;

import IntegrationReports.model.ApiRequestTrack;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.List;

@Repository
public interface ApiRequestTrackRepository extends MongoRepository<ApiRequestTrack,String> {

    @Query(value = "{'order_details.carrier_code':?0, 'order_details.shipper_code': ?1,'created_at': {$gte: ?2, $lte: ?3}}",sort = "{'created_at':-1}")
    List<ApiRequestTrack> findByShipperCodeAndCarrierCodeAndCreatedAtBetween(String key, String eachCode,ZonedDateTime startTime, ZonedDateTime endTime);
}
