package com.mssample.product.model;

import javax.persistence.Transient;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Product model class representing a Product
 * @author Apoorve
 *
 */
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Product {
	private String productId;
	private String displayName;
	private String shortDesc;
	private String description;
	private String category;
	private double price;
	private double deliveryCharge;
	private double offerPrice;
	private Seller seller;
	@Transient
	public double getTotal() {
		return getOfferPrice() + deliveryCharge;
	}
}
