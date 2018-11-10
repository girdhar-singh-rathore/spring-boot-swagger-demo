package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class SpringBootH2DbSwaggerWebAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootH2DbSwaggerWebAppApplication.class, args);
		log.debug("application started.");
	}
}
