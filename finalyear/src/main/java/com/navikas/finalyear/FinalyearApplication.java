package com.navikas.finalyear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication//(exclude = SecurityAutoConfiguration.class)
public class FinalyearApplication {

	public static void main(String[] args) {
		SpringApplication.run(FinalyearApplication.class, args);
	}

}
