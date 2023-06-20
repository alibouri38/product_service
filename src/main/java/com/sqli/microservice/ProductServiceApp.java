package com.sqli.microservice;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.sqli.microservice.entities.Product;
import com.sqli.microservice.entities.ProductCategory;
import com.sqli.microservice.repositories.ProductRepository;

@SpringBootApplication
@EnableDiscoveryClient
public class ProductServiceApp {
	
	@Autowired
	private ProductRepository repository;
	
	final ZonedDateTime now = ZonedDateTime.now();
	
	
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApp.class, args);
    }
  
    
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
       String[] allowDomains = new String[2];
       allowDomains[0] = "http://localhost:4200";
       allowDomains[1] = "http://localhost:8080";

       System.out.println("CORS configuration....");
       return new WebMvcConfigurer() {
          @Override
          public void addCorsMappings(CorsRegistry registry) {
             registry.addMapping("/**").allowedOrigins(allowDomains);
          }
       };
    }
    
   
}