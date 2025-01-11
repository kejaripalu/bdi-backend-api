package id.go.kejaripalu.bdi.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.go.kejaripalu.bdi.security.filter.UsernamePasswordAuthProcessingFilter;
import id.go.kejaripalu.bdi.security.handler.UsernamePasswordAuthFailureHandler;
import id.go.kejaripalu.bdi.security.handler.UsernamePasswordAuthSuccessHandler;
import id.go.kejaripalu.bdi.security.provider.UsernamePasswordAuthProvider;
import id.go.kejaripalu.bdi.security.util.JWTTokenFactory;

@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
@Configuration
public class SecurityConfig {
	
	@Autowired
	private Environment env;
		
	@Autowired
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

    @Bean
    AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JWTTokenFactory jwtTokenFactory) {
		return new UsernamePasswordAuthSuccessHandler(objectMapper, jwtTokenFactory);
	}

    @Bean
    AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper) {
    	return new UsernamePasswordAuthFailureHandler(objectMapper);
    }
	
    @Autowired
    public void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
    	auth.authenticationProvider(usernamePasswordAuthProvider);
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

    @Bean
    UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter(ObjectMapper objectMapper,
    		AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,
    		AuthenticationManager manager) {
		
    	String authUrl = env.getProperty("app.api-url") + "/login";    	
    	UsernamePasswordAuthProcessingFilter filter = 
				new UsernamePasswordAuthProcessingFilter(authUrl, objectMapper, successHandler, failureHandler);
		filter.setAuthenticationManager(manager);		
		return filter;
	}

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, UsernamePasswordAuthProcessingFilter filter) throws Exception {
		http.authorizeHttpRequests(autz -> autz
				.anyRequest().authenticated())
			.csrf(csrf -> csrf.disable())
			.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
					.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
			.httpBasic(Customizer.withDefaults());
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
