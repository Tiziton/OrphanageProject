package pl.polsl.orphanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
public class OrphanageApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrphanageApplication.class, args);
	}
}
