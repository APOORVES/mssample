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
import com.mssample.product.modelui.DealUI;
import com.mssample.product.modelui.ProductDetailsUI;
import com.mssample.product.modelui.ProductUI;
import com.mssample.product.modelui.RecommendationUI;
import com.mssample.product.rest.transform.ProductTransformer;
import com.mssample.product.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	ProductTransformer productTransformer;
	
	@GetMapping(value = "/searchproduct/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductUI>> searchProduct(@PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName);
		List<Product> products = productService.searchProduct(productName); 
		List<ProductUI> productsResponse = productTransformer.transformProducts(products);
		log.info("searchproduct returning productList="+productsResponse);
		ResponseEntity<List<ProductUI>> response = new ResponseEntity<List<ProductUI>>(productsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/deals", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<DealUI>> getDeals(){
		log.info("getDeals invoked");
		List<Product> products = productService.getDeals(); 
		List<DealUI> dealsResponse = productTransformer.transformDeals(products);
		log.info("getDeals returning dealList="+dealsResponse);
		ResponseEntity<List<DealUI>> response = new ResponseEntity<List<DealUI>>(dealsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/{productname}/details", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDetailsUI>> productDetail(@PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName);
		List<Product> products = productService.searchProductExact(productName); 
		List<ProductDetailsUI> productsResponse = productTransformer.transformProductsDetails(products);
		log.info("searchproduct returning productList="+productsResponse);
		ResponseEntity<List<ProductDetailsUI>> response = new ResponseEntity<List<ProductDetailsUI>>(productsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "{userName}/recommendations", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<RecommendationUI>> userRecommendations(@PathVariable("userName") String userName){
		log.info("userDetail invoked with userName="+userName);
		List<Product> products = productService.getRecommendations(userName);
		if(products == null || products.isEmpty())
		{
			throw new ProductNotFoundException("No Purchase history found");
		}
		List<RecommendationUI> recommendationsResponse = productTransformer.transformRecommendations(products);
		log.info("searchproduct returning recommendationsResponse="+recommendationsResponse);
		ResponseEntity<List<RecommendationUI>> response = new ResponseEntity<List<RecommendationUI>>(recommendationsResponse, HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/fetchproduct/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> fetchProduct(@PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName);
		List<Product> products = productService.searchProduct(productName); 
		log.info("searchproduct returning productList="+products);
		ResponseEntity<Product> response = new ResponseEntity<Product>(products.get(0), HttpStatus.OK);
		return response;
	}

	@GetMapping(value = "/fetchproduct/{productcategory}/{productname}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> fetchProduct(@PathVariable("productcategory") String productCategory, @PathVariable("productname") String productName){
		log.info("searchproduct invoked with productName="+productName + " productcategory="+productCategory);
		List<Product> products = productService.searchProduct(productName, productCategory); 
		log.info("searchproduct returning productList="+products);
		ResponseEntity<Product> response = new ResponseEntity<Product>(products.get(0), HttpStatus.OK);
		return response;
	}


}


