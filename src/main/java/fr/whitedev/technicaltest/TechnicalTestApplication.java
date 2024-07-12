package fr.whitedev.technicaltest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
Explain scanBasePackages

- scanBasePackages
scanBasePackages is an attribute allowing you to specify the package base to scan for the different components (configurations, controller, services, etc.).
*/

@SpringBootApplication(scanBasePackages = "fr.whitedev.technicaltest")
public class TechnicalTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechnicalTestApplication.class, args);
	}
}
