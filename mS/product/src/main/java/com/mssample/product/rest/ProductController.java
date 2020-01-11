package com.mssample.product.rest;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mssample.product.exception.ProductNotFoundException;
import com.mssample.product.model.Product;
import com.mssample.product.rest.transform.ProductTransformer;
import com.mssample.product.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductTransformer productTransformer;
	
	@GetMapping(value = "/searchproduct/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, String>>> searchProduct(@PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName);
		List<Product> products = productService.searchProduct(productName); 
		List<Map<String, String>> productsResponse = productTransformer.transformProducts(products);
		log.info("searchproduct returning productList="+productsResponse);
		ResponseEntity<List<Map<String, String>>> response = new ResponseEntity<List<Map<String, String>>>(productsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/deals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, String>>> getDeals(){
		log.info("getDeals invoked");
		List<Product> products = productService.getDeals(); 
		List<Map<String, String>> dealsResponse = productTransformer.transformDeals(products);
		log.info("getDeals returning dealList="+dealsResponse);
		ResponseEntity<List<Map<String, String>>> response = new ResponseEntity<List<Map<String, String>>>(dealsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{productname}/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, String>>> productDetail(@PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName);
		List<Product> products = productService.searchProductExact(productName); 
		List<Map<String, String>> productsResponse = productTransformer.transformProductsDetails(products);
		log.info("searchproduct returning productList="+productsResponse);
		ResponseEntity<List<Map<String, String>>> response = new ResponseEntity<List<Map<String, String>>>(productsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "{userName}/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Map<String, String>>> userDetail(@PathVariable("userName") String userName){
		log.info("userDetail invoked with userName="+userName);
		List<Product> products = productService.getRecommendations(userName);
		if(products == null || products.isEmpty())
		{
			throw new ProductNotFoundException("No Purchase history found");
		}
		List<Map<String, String>> recommendationsResponse = productTransformer.transformRecommendations(products);
		log.info("searchproduct returning recommendationsResponse="+recommendationsResponse);
		ResponseEntity<List<Map<String, String>>> response = new ResponseEntity<List<Map<String, String>>>(recommendationsResponse, HttpStatus.OK);
		return response;
	}

}


