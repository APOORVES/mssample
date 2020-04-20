package com.mssample.cart.modelui.request;


import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Request from Ui to modify cart
 * @author Apoorve
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ModifyCartRequestUI {
	//These messages are default messages and are overridden in property files
	/**
	 * 
	 */
	@NotNull(message = "Product name cannot be empty")
	private String productName;
	/**
	 * 
	 */
	@NotNull(message = "Seller name cannot be empty")
	private String sellerName;
	/**
	 * 
	 */
	@Max(value = 4, message = "Maximum allowed quantity for any item is 4")
	@NotNull(message = "Quantity cannot be empty")
	private int quantity;
	/**
	 * 
	 */
	@NotNull(message = "Cart offer price cannot be empty")
	private double cartOfferPrice;
}
