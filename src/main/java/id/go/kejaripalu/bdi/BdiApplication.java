package id.go.kejaripalu.bdi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin(origins = "*")
public class BdiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BdiApplication.class, args);
	}

}
