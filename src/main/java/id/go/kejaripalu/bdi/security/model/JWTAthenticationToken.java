package id.go.kejaripalu.bdi.security.model;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class JWTAthenticationToken extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -7388175102460044006L;

	private RawAccesJWTToken rawAccessJWTToken;
	
	private UserDetails userDetails;
	
	public JWTAthenticationToken(RawAccesJWTToken rawAccessJWTToken) {
		super(null);
		this.rawAccessJWTToken = rawAccessJWTToken;
		super.setAuthenticated(false);
	}
	
	public JWTAthenticationToken(UserDetails userDetails, Collection<? extends GrantedAuthority> authorities) {
		super(authorities);
		eraseCredentials();
		this.userDetails = userDetails;
		super.setAuthenticated(true);
	}

	@Override
	public Object getCredentials() {
		return rawAccessJWTToken;
	}

	@Override
	public Object getPrincipal() {
		return userDetails;
	}

	@Override
	public void eraseCredentials() {
		super.eraseCredentials();
		rawAccessJWTToken = null;
	}	

}
