package com.example.buoi01;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@SpringBootApplication
@SpringBootApplication(exclude = org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class)
public class Buoi01Application {

	public static void main(String[] args) {
		SpringApplication.run(Buoi01Application.class, args);
	}

}
