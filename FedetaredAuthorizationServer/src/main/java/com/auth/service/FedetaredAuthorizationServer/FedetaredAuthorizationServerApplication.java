package com.auth.service.FedetaredAuthorizationServer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class }, scanBasePackages = {
		"com.auth.service.FedetaredAuthorizationServer", "com.auth.service.controller", "com.auth.service.config" ,"com.auth.service.model"})
public class FedetaredAuthorizationServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(FedetaredAuthorizationServerApplication.class, args);
	}

}
