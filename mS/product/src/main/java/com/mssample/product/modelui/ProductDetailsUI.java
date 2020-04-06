package com.mssample.product.modelui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductDetailsUI {
	private String displayName;
	private String shortDesc;
	private String desc;
	private String category;
	private String price;
	private String discount;
	private String deliveryCharge;
	private String offerPrice;
	private String seller;
	private String sellerCount;	
	private String avgRating;	
	private String reviews;	
}
