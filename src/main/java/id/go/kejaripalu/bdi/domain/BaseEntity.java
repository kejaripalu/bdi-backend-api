package id.go.kejaripalu.bdi.domain;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity implements Serializable {

	@Serial
    private static final long serialVersionUID = 2420627709994200519L;

	@UuidGenerator
	@Column(name = "id_secure", nullable = false, updatable = false, unique = true)
	private String ids;
	
	@JsonIgnore
	private boolean deleted = false;
	
	@JsonIgnore
	@CreationTimestamp
	@Column(updatable = false, name = "create_time")
	private LocalDateTime createAt;
	
	@JsonIgnore
	@UpdateTimestamp
	@Column(name = "update_time")
	private LocalDateTime updateAt;
	
}
