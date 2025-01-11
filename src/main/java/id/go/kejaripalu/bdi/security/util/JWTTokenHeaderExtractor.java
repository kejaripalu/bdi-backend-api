package id.go.kejaripalu.bdi.security.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.stereotype.Component;

@Component
public class JWTTokenHeaderExtractor implements JWTTokenExtractor {

	private static final String HEADER_PREFIX = "Bearer ";
	
	@Override
	public String extract(String payload) {
		
		if (StringUtils.isBlank(payload)) {
			throw new AuthenticationServiceException("AUTHORIZATION_MISSING_OR_EMPTY");
		}
		if (payload.length() <= HEADER_PREFIX.length()) {
			throw new AuthenticationServiceException("AUTHORIZATION_INVALID");	
		}
		
		return payload.substring(HEADER_PREFIX.length(), payload.length());
	}

}
