package id.go.kejaripalu.bdi.config;

import java.security.Key;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.fasterxml.jackson.databind.ObjectMapper;

import id.go.kejaripalu.bdi.security.util.JWTTokenFactory;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;


@Configuration
public class ApplicationConfig {
	
	@Autowired
	Environment env;
	
	@Bean
	SecretKey key() {
		byte[] keyBites = Decoders.BASE64.decode(env.getProperty("app.random-code"));
		return Keys.hmacShaKeyFor(keyBites);
	}
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

    @Bean
    ObjectMapper objectMapper() {
		return new ObjectMapper();
	}
    
    @Bean
    JWTTokenFactory jwtTokenFactory(Environment env, Key key) {
    	return new JWTTokenFactory(env, key);
    }

}
