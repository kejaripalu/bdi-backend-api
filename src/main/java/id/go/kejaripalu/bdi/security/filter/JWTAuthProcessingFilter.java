package id.go.kejaripalu.bdi.security.filter;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.RequestMatcher;

import id.go.kejaripalu.bdi.security.model.AnonymousAuthentication;
import id.go.kejaripalu.bdi.security.model.JWTAthenticationToken;
import id.go.kejaripalu.bdi.security.model.RawAccesJWTToken;
import id.go.kejaripalu.bdi.security.util.JWTTokenExtractor;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthProcessingFilter extends AbstractAuthenticationProcessingFilter {
	
	private JWTTokenExtractor tokenExtractor;
	
	private AuthenticationFailureHandler failureHandler;
	
	public JWTAuthProcessingFilter(RequestMatcher requiresAuthenticationRequestMatcher,
			JWTTokenExtractor tokenExtractor, AuthenticationFailureHandler failureHandler) {
		super(requiresAuthenticationRequestMatcher);
		this.tokenExtractor = tokenExtractor;
		this.failureHandler = failureHandler;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		//intercept, mengambil header dari request
		String authorizationHeader = request.getHeader("Authorization");
		
		//extract header untuk mendapatkan jwt
		String jwt = tokenExtractor.extract(authorizationHeader);
		
		//bungkus object token untuk proses otentikasi
		RawAccesJWTToken rawToken = new RawAccesJWTToken(jwt);
		
		return super.getAuthenticationManager().authenticate(new JWTAthenticationToken(rawToken));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();
		context.setAuthentication(authResult); //mengambil hasil verifikasi dari inject Authentication dari method attemptAuthentication() 
		SecurityContextHolder.setContext(context);
		chain.doFilter(request, response);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException failed) throws IOException, ServletException {		
		SecurityContextHolder.clearContext(); //Mencegah apabila ada user yang sebelumnya sudah terverifikasi
		SecurityContextHolder.getContext().setAuthentication(new AnonymousAuthentication());;	
		failureHandler.onAuthenticationFailure(request, response, failed);
	}	
	
}
