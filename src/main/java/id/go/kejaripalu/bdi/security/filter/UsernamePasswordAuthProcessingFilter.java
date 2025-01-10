package id.go.kejaripalu.bdi.security.filter;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.go.kejaripalu.bdi.exception.BadRequestException;
import id.go.kejaripalu.bdi.security.dto.LoginRequestDTO;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UsernamePasswordAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {

	private final ObjectMapper objectMapper;
	
	private final AuthenticationSuccessHandler successHandler;
	
	private final AuthenticationFailureHandler failureHandler;

	public UsernamePasswordAuthProcessingFilter(String defaultFilterProcessesUrl, ObjectMapper objectMapper,
			AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler) {
		
		super(defaultFilterProcessesUrl);
		this.objectMapper = objectMapper;
		this.successHandler = successHandler;
		this.failureHandler = failureHandler;
	}

	/**
	 * Menthod ini berfungsi melakukan authentikasi terhadap request yang di-intercept
	 * method ini bertipe interface Authentication yang akan merepresentasikan token setiap
	 * melakukan proses request autentikasi. Method ini ibarat kunci/key setiap melakukan request.
	 */	 
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		LoginRequestDTO dto = objectMapper.readValue(request.getReader(), LoginRequestDTO.class);
		
		if (StringUtils.isBlank(dto.getUsername()) || StringUtils.isBlank(dto.getPassword())) {
			throw new BadRequestException("USERNAME_PASSWORD_REQUIRED");
		}
		
		UsernamePasswordAuthenticationToken token = 
				new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());
		
		return this.getAuthenticationManager().authenticate(token);
	}

	// Menthod ini akan berfungsi jika autentikasi sukses
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		this.successHandler.onAuthenticationSuccess(request, response, authResult);
	}
	
	// Menthod ini akan berfungsi jika autentikasi gagal
	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {
		
		this.failureHandler.onAuthenticationFailure(request, response, failed);
	}
	
}
