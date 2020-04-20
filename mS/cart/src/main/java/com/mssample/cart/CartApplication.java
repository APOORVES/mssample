package com.mssample.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author Apoorve
 * Class representing the springboot Cart Application
 */
@SpringBootApplication
public class CartApplication {

	/** Spring boot application main method
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(CartApplication.class, args);
	}
	
	/**
	 * @return resttemplate to be used to access other microservices
	 */
	@Bean
	public RestTemplate getRestTemplate(){
		RestTemplate restTemplate = new RestTemplate();
		return restTemplate;
	}

}
