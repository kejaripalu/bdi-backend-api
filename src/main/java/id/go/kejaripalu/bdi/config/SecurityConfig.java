package id.go.kejaripalu.bdi.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
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
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.go.kejaripalu.bdi.security.filter.JWTAuthProcessingFilter;
import id.go.kejaripalu.bdi.security.filter.UsernamePasswordAuthProcessingFilter;
import id.go.kejaripalu.bdi.security.handler.UsernamePasswordAuthFailureHandler;
import id.go.kejaripalu.bdi.security.handler.UsernamePasswordAuthSuccessHandler;
import id.go.kejaripalu.bdi.security.provider.JWTAuthenticationAuthProvider;
import id.go.kejaripalu.bdi.security.provider.UsernamePasswordAuthProvider;
import id.go.kejaripalu.bdi.security.util.JWTTokenExtractor;
import id.go.kejaripalu.bdi.security.util.JWTTokenFactory;
import id.go.kejaripalu.bdi.security.util.SkipPathRequestMatcher;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class SecurityConfig {

	@Autowired
	private Environment env;

	@Autowired
	private UsernamePasswordAuthProvider usernamePasswordAuthProvider;

	@Autowired
	private JWTAuthenticationAuthProvider jwtAuthenticationAuthProvider;
	
	
	//tambahkan url endpoint yang permit dan authenticated sesuai kebutuhan, ubah ke List/Collection jika dibutuhkan
	private String getAuthUrl() {
		return env.getProperty("app.api-url") + "/login";
	}
	
	private String getApiUrl() {
		return env.getProperty("app.api-url") + "/**";
	}

	@Bean
	AuthenticationSuccessHandler successHandler(ObjectMapper objectMapper, JWTTokenFactory jwtTokenFactory) {
		return new UsernamePasswordAuthSuccessHandler(objectMapper, jwtTokenFactory);
	}

	@Bean
	AuthenticationFailureHandler failureHandler(ObjectMapper objectMapper) {
		return new UsernamePasswordAuthFailureHandler(objectMapper);
	}

	@Autowired
	void registerProvider(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(usernamePasswordAuthProvider)
			.authenticationProvider(jwtAuthenticationAuthProvider);
	}

	@Bean
	AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
		return configuration.getAuthenticationManager();
	}

	@Bean
	UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter(ObjectMapper objectMapper,
			AuthenticationSuccessHandler successHandler, AuthenticationFailureHandler failureHandler,
			AuthenticationManager manager) {

		UsernamePasswordAuthProcessingFilter filter = new UsernamePasswordAuthProcessingFilter(this.getAuthUrl(), objectMapper,
				successHandler, failureHandler);
		filter.setAuthenticationManager(manager);
		return filter;
	}

	@Bean
	JWTAuthProcessingFilter jwtAuthProcessingFilter(JWTTokenExtractor tokenExtractor,
			AuthenticationFailureHandler failureHandler,
			AuthenticationManager manager) {

		//tambahkan url endpoint yang permit dan authenticated sesuai kebutuhan ke dalam
		//List yang ada pada variabel permitEndPointList dan autheticatedEndPointList		
		List<String> permitEndPointList = Arrays.asList(this.getAuthUrl());
		List<String> autheticatedEndPointList = Arrays.asList(this.getApiUrl());

		SkipPathRequestMatcher matcher = new SkipPathRequestMatcher(permitEndPointList, autheticatedEndPointList);				
		JWTAuthProcessingFilter filter = new JWTAuthProcessingFilter(matcher, tokenExtractor, failureHandler);
		filter.setAuthenticationManager(manager);
		return filter;
	}
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList(env.getProperty("app.origin-url")));
		configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
		configuration.setAllowedHeaders(Arrays.asList("*"));
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http,
			UsernamePasswordAuthProcessingFilter usernamePasswordAuthProcessingFilter,
			JWTAuthProcessingFilter jwtAuthProcessingFilter) throws Exception {
		http.authorizeHttpRequests(autz -> 
			autz.requestMatchers(HttpMethod.DELETE).hasAnyRole("USER", "ADMIN", "SUPERADMIN")
				.requestMatchers(HttpMethod.PUT).hasAnyRole("USER", "ADMIN", "SUPERADMIN")
				.requestMatchers(HttpMethod.POST).hasAnyRole("USER", "ADMIN", "SUPERADMIN")
				.requestMatchers(HttpMethod.GET).hasAnyRole("GUEST", "USER", "ADMIN", "SUPERADMIN")
				.requestMatchers(this.getAuthUrl() + "/**").permitAll()				
				.requestMatchers(this.getApiUrl()).authenticated())
				.cors(cors -> cors.configurationSource(corsConfigurationSource()))
				.csrf(csrf -> csrf.disable())
				.sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer
						.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		http.addFilterBefore(usernamePasswordAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class)
			.addFilterBefore(jwtAuthProcessingFilter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}

}
