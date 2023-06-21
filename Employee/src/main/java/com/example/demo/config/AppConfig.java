package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import jakarta.annotation.PostConstruct;

@Configuration
@Profile("test")	//This class will be execute only when if our test profile is activated. It means in our default application.properties if our active profile=test/dev/prod
public class AppConfig {
 
	@PostConstruct
	public void print() {
		System.out.println("It will execute after creating bean of each class.");
	}
}
