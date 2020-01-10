package com.mssample.product.rest.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mssample.product.model.Product;

@Component
public class ProductTransformer {
	public List<Map<String, String>> transformProducts(List<Product> products)
	{
		List<Map<String, String>> result; // = new ArrayList<Map<String, String>>()
		result = products.stream().map(product->
											{
												Map<String, String> resultMap = new HashMap<String,String>();
												resultMap.put("displayName", product.getDisplayName());
												resultMap.put("shortDesc", product.getShortDesc());
												resultMap.put("description", product.getDescription());
												resultMap.put("category", product.getCategory());
												resultMap.put("price", ""+product.getPrice());
												resultMap.put("discount", ""+product.getDeal().getDiscount());
												resultMap.put("deliveryCharge", ""+product.getDeliveryCharge());
												return resultMap;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}
	
	public List<Map<String, String>> transformDeals(List<Product> products)
	{
		List<Map<String, String>> result; // = new ArrayList<Map<String, String>>()
		result = products.stream().map(product->
											{
												Map<String, String> resultMap = new HashMap<String,String>();
												resultMap.put("displayName", product.getDisplayName());
												resultMap.put("shortDesc", product.getShortDesc());
												resultMap.put("category", product.getCategory());
												resultMap.put("discount", ""+product.getDeal().getDiscount());
												return resultMap;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}

	public List<Map<String, String>> transformProductsDetails(List<Product> products) {
		List<Map<String, String>> result; // = new ArrayList<Map<String, String>>()
		result = products.stream().map(product->
											{
												Map<String, String> resultMap = new HashMap<String,String>();
												resultMap.put("displayName", product.getDisplayName());
												resultMap.put("shortDesc", product.getShortDesc());
												resultMap.put("desc", product.getDescription());
												resultMap.put("category", product.getCategory());
												resultMap.put("price", ""+product.getPrice());
												resultMap.put("discount", ""+product.getDeal().getDiscount());
												resultMap.put("deliveryCharge", ""+product.getDeliveryCharge());
												resultMap.put("offerPrice", ""+product.getOfferPrice());
												resultMap.put("seller", ""+product.getSeller().getSellerName());
												resultMap.put("sellerCount", ""+product.getSeller().getSellerCount());
												resultMap.put("avgRating", ""+product.getAvgRating());
												resultMap.put("reviews", ""+product.getRatings());
												return resultMap;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}

}
