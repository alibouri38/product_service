package com.sqli.microservice;

import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.server.adapter.ForwardedHeaderTransformer;

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
    ForwardedHeaderTransformer forwardedHeaderTransformer() {
        return new ForwardedHeaderTransformer();
    }
    
   
}