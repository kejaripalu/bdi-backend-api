package id.go.kejaripalu.bdi.security.dto;

import java.io.Serializable;

import lombok.Data;

@Data
public class LoginRequestDTO implements Serializable {

	private static final long serialVersionUID = 5470567096248735196L;

	private String username;
	
	private String password;
	
}
