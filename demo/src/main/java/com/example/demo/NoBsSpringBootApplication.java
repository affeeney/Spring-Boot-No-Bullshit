package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NoBsSpringBootApplication {

	public static void main(String[] args) {
		System.out.println("Hi nina");
		SpringApplication.run(NoBsSpringBootApplication.class, args);
	}

}
