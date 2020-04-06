package com.mssample.cart.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CART_ITEM")
@Data @NoArgsConstructor @AllArgsConstructor
public class CartItem {
	@Id
	@Column(name="CART_ITEM_ID")
	private String cartItemId;
	@Column(name="PRODUCT_ID")
	private String productId;
	@OneToMany(mappedBy = "productId",cascade = CascadeType.ALL)
	private Product product;
	@Column(name="QUANTITY")
	private int quantity;
	@Column(name="OFFER_PRICE")
	private double offerPrice;
	@Column(name="CART_ID")
	private String cartId;
	public CartItem(String productId, Product product, int quantity, double offerPrice) {
		super();
		this.productId = productId;
		this.product = product;
		this.quantity = quantity;
		this.offerPrice = offerPrice;
	}
	
}