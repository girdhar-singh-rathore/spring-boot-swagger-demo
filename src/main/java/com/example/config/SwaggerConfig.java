package com.example.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final springfox.documentation.service.Contact CONTECT = new springfox.documentation.service.Contact(
			"Girdhar Singh Rathore", "https://github.com/girdhar-singh-rathore/", "com.example.com");

	public static final ApiInfo API_INFO = new ApiInfo("Swagger Demo", "Spring Boot Swagger example", "1.0",
			"All right reserved", CONTECT, "1.0", "licenseUrl");

	private static final Set<String> CONSUME_AND_PRODUCE = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO).consumes(CONSUME_AND_PRODUCE)
				.produces(CONSUME_AND_PRODUCE);
	}
}
