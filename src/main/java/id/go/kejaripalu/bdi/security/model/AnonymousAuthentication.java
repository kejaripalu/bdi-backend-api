package id.go.kejaripalu.bdi.security.model;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AnonymousAuthentication extends AbstractAuthenticationToken {

	private static final long serialVersionUID = -7626194511726787642L;

	public AnonymousAuthentication() {
		super(null); //set null karena user tidak ter-autentikasi
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
	
	@Override
	public boolean isAuthenticated() {
		return false;
	}

}
