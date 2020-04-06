package com.mssample.product.rest.transform;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.mssample.product.model.Product;
import com.mssample.product.modelui.DealUI;
import com.mssample.product.modelui.ProductDetailsUI;
import com.mssample.product.modelui.ProductUI;
import com.mssample.product.modelui.RecommendationUI;

@Component
public class ProductTransformer {
	public List<ProductUI> transformProducts(List<Product> products)
	{
		List<ProductUI> result; // = new ArrayList<Map<String, String>>()
		result = products.stream().map(product->
											{
												ProductUI productUI = new ProductUI(
												product.getDisplayName(), product.getShortDesc(), product.getDescription(), 
												product.getCategory(), ""+product.getPrice(), product.getDeal()!=null?""+product.getDeal().getDiscount():"", 
														""+product.getDeliveryCharge());
												return productUI;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}
	
	public List<DealUI> transformDeals(List<Product> products)
	{
		List<DealUI> result;
		result = products.stream().map(product->
											{
												DealUI dealUI = new DealUI(product.getDisplayName(),
												product.getShortDesc(), product.getCategory(),
												product.getDeal()!=null?""+product.getDeal().getDiscount():"");
												return dealUI;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}

	public List<ProductDetailsUI> transformProductsDetails(List<Product> products) {
		List<ProductDetailsUI> result; // = new ArrayList<Map<String, String>>()
		result = products.stream().map(product->
											{
												ProductDetailsUI productDetailsUI = new ProductDetailsUI(product.getDisplayName(), product.getShortDesc(), 
														product.getDescription(), product.getCategory(), ""+  product.getPrice(),  
														product.getDeal()!=null?""+product.getDeal().getDiscount():"", ""+product.getDeliveryCharge(), 
														""+product.getOfferPrice(), product.getSeller()!=null?""+product.getSeller().getSellerName():"", 
														product.getSeller()!=null?""+product.getSeller().getSellerCount():"",  ""+product.getAvgRating(),
														""+product.getRatings());
												return productDetailsUI;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}

	public List<RecommendationUI> transformRecommendations(List<Product> products) {
		List<RecommendationUI> result;
		result = products.stream().map(product->
											{
												RecommendationUI recommendationUI = new RecommendationUI(product.getDisplayName(), 
														product.getShortDesc(), product.getCategory());
												return recommendationUI;
											}
								  ).collect(Collectors.toList());
		
		return result;
	}

}
