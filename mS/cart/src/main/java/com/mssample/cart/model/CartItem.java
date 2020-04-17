package com.mssample.cart.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "CART_ITEM")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="cartitem_seq", initialValue=1, allocationSize=100)
public class CartItem {
	@Id
	@Column(name="CART_ITEM_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cartitem_seq")
	private Long cartItemId;
	@Column(name="PRODUCT_DISPLAY_NAME")
	private String productDisplayName;
	@Column(name="QUANTITY")
	private int quantity;
	@Column(name="OFFER_PRICE")
	private double offerPrice;
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="CART_ID")
	private Cart cart;
	public CartItem(String productDisplayName, int quantity, double offerPrice, Cart cart) {
		super();
		this.productDisplayName = productDisplayName;
		this.quantity = quantity;
		this.offerPrice = offerPrice;
		this.cart = cart;
	}
	
}