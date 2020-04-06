package com.mssample.cart.modelui.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class ModifyCartRequestUI {
	@NotNull(message = "{com.mssample.cart.productName.notEmpty}")
	private String productName;
	@NotNull(message = "{com.mssample.cart.sellerName.notEmpty}")
	private String sellerName;
	@Max(value = 4, message = "{com.mssample.cart.quantity.max}")
	@NotNull(message = "{com.mssample.cart.quantity.notEmpty}")
	private int quantity;
	@NotNull(message = "{com.mssample.cart.cartOfferPrice.notEmpty}")
	private double cartOfferPrice;
}
