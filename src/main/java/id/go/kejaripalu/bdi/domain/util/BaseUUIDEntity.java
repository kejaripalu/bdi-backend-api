package id.go.kejaripalu.bdi.domain.util;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseUUIDEntity {

	@Id
	@UuidGenerator
	@Column(nullable = false, updatable = false)
	private String id;
	
	private Boolean deleted = false;
	
}
