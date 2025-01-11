package id.go.kejaripalu.bdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Configuration
@ConfigurationProperties(prefix = "app")
@Data
public class ApplicationProperties {
	
	private String originUrl;
	private String apiUrl;
	private String timeZone;
	private Integer tokenExpired;
	private String zoneId;
	private String issuer;
	private String randomCode;
	
}
