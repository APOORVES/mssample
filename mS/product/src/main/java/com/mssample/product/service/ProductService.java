package com.mssample.product.service;

import java.sql.Timestamp;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssample.product.exception.DealNotFoundException;
import com.mssample.product.exception.ProductNotFoundException;
import com.mssample.product.model.Deal;
import com.mssample.product.model.Product;
import com.mssample.product.repo.DealRepository;
import com.mssample.product.repo.ProductRepository;
import com.mssample.product.repo.PurchaseHistoryRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProductService {
	@Autowired 
	ProductRepository productRepository;
	@Autowired 
	DealRepository dealRepository;
	@Autowired 
	PurchaseHistoryRepository purchaseHistoryRepository;
	
	public List<Product> searchProduct(String displayName){
		List<Product> products = productRepository.findByDisplayNameLike(displayName + "%"); 
		log.debug("Size of Product List="+(products!=null?products.size():null));
		if(products == null || products.size() == 0)
			throw new ProductNotFoundException("No Products Found matching the search criteria");
		return products;
	}

	public List<Product> searchProduct(String displayName, String productCategory){
		List<Product> products = productRepository.findByDisplayNameAndCategory(displayName, productCategory); 
		log.debug("Size of Product List="+(products!=null?products.size():null));
		if(products == null || products.size() == 0)
			throw new ProductNotFoundException("No Products Found matching the search criteria");
		return products;
	}
	
	public List<Product> searchProductExact(String displayName){
		List<Product> products = productRepository.findByDisplayName(displayName); 
		log.debug("Size of Product List="+(products!=null?products.size():null));
		if(products == null || products.size() == 0)
			throw new ProductNotFoundException("No Products Found matching the search criteria");
		return products;
	}

	public List<Product> getDeals() {
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		List<Deal> deals = dealRepository.findByStartDateLessThanAndEndDateGreaterThanOrderByDiscountDesc(ts, ts); 
		List<Product> products = deals.stream().map(deal->deal.getProduct()).collect(Collectors.toList());
		log.debug("Size of Product List="+(products!=null?products.size():null));
		if(products == null || products.size() == 0)
			throw new DealNotFoundException("No Deals Found for today");
		return products;
	}

	public List<Product> getRecommendations(String userName) {
		List<Product> products = purchaseHistoryRepository.findByName(userName);
		return products;
	}

}
