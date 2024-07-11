package fr.whitedev.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// TODO: explain scanBasePackages

@SpringBootApplication(scanBasePackages = "fr.whitedev.technicaltest")
public class TechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}
}
