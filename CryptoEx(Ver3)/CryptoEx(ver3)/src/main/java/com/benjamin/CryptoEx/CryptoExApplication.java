package com.benjamin.CryptoEx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class CryptoExApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoExApplication.class, args);
	}

}
