package com.fulda.iuliiashtal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main entry point for the Web Shop application.
 * <p>
 * This class is responsible for bootstrapping and launching the Spring Boot application.
 * It is annotated with {@link SpringBootApplication}, which combines several Spring
 * annotations to enable component scanning, auto-configuration, and property support.
 * </p>
 *
 * To start the application, execute the {@code main} method.
 */
@SpringBootApplication
public class WebShopStartApplication {

	/**
	 * Main method to launch the Web Shop application.
	 * <p>
	 * This method uses {@link SpringApplication#run(Class, String[])} to start the application
	 * context and initialize all configured components.
	 * </p>
	 *
	 * @param args optional command-line arguments that can be passed to the application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(WebShopStartApplication.class, args);
	}
}
