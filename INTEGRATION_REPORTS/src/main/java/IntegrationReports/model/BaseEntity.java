package IntegrationReports.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
public class BaseEntity {

	@Id
	protected String id;

	@Field(value = "created_at")
	@CreatedDate
	private LocalDateTime createdAt;

	@Field(value = "updated_at")
	@LastModifiedDate
	private LocalDateTime updatedAt;

	@Field(value = "deleted_at")
	private LocalDateTime deletedAt;
}
