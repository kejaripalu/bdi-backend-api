package id.go.kejaripalu.bdi.security.provider;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import id.go.kejaripalu.bdi.security.service.AppUserService;
import lombok.AllArgsConstructor;

/**
 * AuthenticationProvider adalah mekanisme autentikasi dapat dibuka.
 */
@Component
@AllArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

	private final AppUserService userService;
	
	private final PasswordEncoder passwordEncoder;
	
	// Mekanisme proses autentikasi dibuka
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {		
		String username = (String) authentication.getPrincipal();
		String password = (String) authentication.getCredentials();		
		UserDetails userDetails = userService.loadUserByUsername(username);
		
		if (!passwordEncoder.matches(password, userDetails.getPassword())) {
			throw new BadCredentialsException("INVALID_USERNAME_PASSWORD");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
	}

	// Mengembalikan nilai true jika token mendukung mekanisme autentikasi
	@Override
	public boolean supports(Class<?> authentication) {		
		return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
	}

}
