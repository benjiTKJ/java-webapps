package com.fdmgroup.first_web_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

/**
 * View class to start the web application
 * @author Benjamin Tan 
 * @version 0.0.1-SNAPSHOT 
 * 
 */
@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class FirstWebAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstWebAppApplication.class, args);
	}

}
