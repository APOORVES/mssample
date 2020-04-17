package com.mssample.product;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

import com.mssample.product.model.Product;
import com.mssample.product.repo.ProductRepository;
import com.mssample.product.testdata.AllData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootApplication
public class ProductApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(ProductApplication.class, args);
		testDataSetup(context);
	}
	
	@Bean RestTemplate restTemplate(){
		return new RestTemplate();
	}
	
	private static void testDataSetup(ConfigurableApplicationContext context) {
		ProductRepository productRepository = context.getBean(ProductRepository.class);
		List<Product> products = AllData.createProducts();
		for(Product product:products) {
			try
			{
				productRepository.saveAndFlush(product);
			}
			catch(Exception e)
			{
				log.debug("Ignore this exception due to data already existing in DB");
				e.printStackTrace();
			}
		}
	}

	 @Bean public PasswordEncoder passwordEncoder() { 
		 return new BCryptPasswordEncoder(); 
	 }

	
}
