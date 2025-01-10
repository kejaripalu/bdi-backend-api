package id.go.kejaripalu.bdi.security.domain;

import org.springframework.security.core.GrantedAuthority;

import id.go.kejaripalu.bdi.security.domain.util.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "role")
@Setter
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Role extends BaseEntity implements GrantedAuthority {
	
	private static final long serialVersionUID = -4392048428175218817L;
	
	@Column(name = "name", nullable = false)
	private String name;

	@Override
	public String getAuthority() {
		return "ROLE_" + name;
	}	

}
