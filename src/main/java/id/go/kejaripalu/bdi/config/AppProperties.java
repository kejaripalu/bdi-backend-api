package id.go.kejaripalu.bdi.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "app")
@Setter
@Getter
public class AppProperties {

	private String name;
	private String timezone;
	
}
