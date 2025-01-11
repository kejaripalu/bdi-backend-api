package id.go.kejaripalu.bdi.security.provider;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import id.go.kejaripalu.bdi.security.model.JWTAthenticationToken;
import id.go.kejaripalu.bdi.security.model.RawAccesJWTToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class JWTAuthenticationAuthProvider implements AuthenticationProvider {
	
	private final SecretKey key;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		
		//authentication -> JWTAuthentication -> getCredential -> rawAccessJWTToken
		RawAccesJWTToken token = (RawAccesJWTToken) authentication.getCredentials();
		
		//verifikasi dan parsing data isi token
		Jws<Claims> jwsClaims = token.parseClaim(key);
		String subject = jwsClaims.getPayload().getSubject();
		@SuppressWarnings("unchecked")
		List<String> scopes = jwsClaims.getPayload().get("scopes", List.class);
		List<GrantedAuthority> authorities = 
				scopes.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		
		//masukan detailnya -> userDetails
		UserDetails userDetails = new UserDetails() {
			
			private static final long serialVersionUID = 5580123501615833159L;

			@Override
			public boolean isAccountNonExpired() {
				return true;
			}

			@Override
			public boolean isAccountNonLocked() {
				return true;
			}

			@Override
			public boolean isCredentialsNonExpired() {
				return true;
			}

			@Override
			public boolean isEnabled() {
				return true;
			}

			@Override
			public String getUsername() {
				return subject;
			}
			
			@Override
			public String getPassword() {
				return null;
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {
				return authorities;
			}
		};
		
		//JWTAuthenticationToken yang verified
		return new JWTAthenticationToken(userDetails, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return JWTAthenticationToken.class.isAssignableFrom(authentication);
	}

}
