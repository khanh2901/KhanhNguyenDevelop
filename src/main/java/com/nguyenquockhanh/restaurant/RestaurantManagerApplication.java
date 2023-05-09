package com.nguyenquockhanh.restaurant;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ComponentScan(basePackages ={"com.nguyenquockhanh.restaurant"})
@EnableAutoConfiguration
public class RestaurantManagerApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(RestaurantManagerApplication.class, args);
		
	}
	
	
}
