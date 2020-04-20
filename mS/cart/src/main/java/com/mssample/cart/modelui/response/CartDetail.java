package com.mssample.cart.modelui.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Cart Details (products and their quantities in the cart)
 * @author Apoorve
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartDetail {
	/**
	 * Display name of the product
	 */
	private String displayName;
	/**
	 * Seller Name
	 */
	private String category;
	/**
	 * Seller Name
	 */
	private String sellerName;
	/**
	 * Cart Price
	 */
	private double price;
	/**
	 * Cart Delivery charge
	 */
	private double deliveryCharge;
	/**
	 * cart quantity
	 */
	private int  quantity;
	/**
	 * Cart item total price
	 */
	private double total;
	/**
	 * cart item offer price
	 */
	private double cartOfferPrice;
}
