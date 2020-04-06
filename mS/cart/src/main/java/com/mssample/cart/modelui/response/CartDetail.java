package com.mssample.cart.modelui.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartDetail {
	private String displayName;
	private String category;
	private String sellerName;
	private double price;
	private double deliveryCharge;
	private int  quantity;
	private double total;
	private double cartOfferPrice;
}
