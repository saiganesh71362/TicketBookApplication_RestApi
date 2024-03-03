package com.main.swagger;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfigue
{
	@Bean
	public GroupedOpenApi controllerApi() {
	        return GroupedOpenApi.builder()
	                .group("Ticket-api")
	                .packagesToScan("com.main.controller") // Specify the package to scan
	                .build();
	    }
}
