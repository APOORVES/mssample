package com.mssample.product.modelui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ProductUI {
	private String displayName;
	private String shortDesc;
	private String description;
	private String category;
	private String price;
	private String discount;
	private String deliveryCharge;

}
