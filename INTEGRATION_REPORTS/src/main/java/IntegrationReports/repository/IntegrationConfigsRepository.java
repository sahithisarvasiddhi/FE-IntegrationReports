package IntegrationReports.repository;

import IntegrationReports.model.IntegrationConfigs;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegrationConfigsRepository extends MongoRepository<IntegrationConfigs, String> {

}