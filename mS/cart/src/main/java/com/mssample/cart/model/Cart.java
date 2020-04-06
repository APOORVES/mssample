package com.mssample.cart.model;

import java.util.List;

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
@Table(name = "CART")
@Data @NoArgsConstructor @AllArgsConstructor
public class Cart {
	@Id
	@Column(name="CART_ID")
	private String cartId;
	@Column(name="USER_ID")
	private User user;
	@OneToMany(mappedBy = "cartId",cascade = CascadeType.ALL)
	private List<CartItem> cartSelections;
	public Cart(User user, List<CartItem> cartSelections) {
		super();
		this.user = user;
		this.cartSelections = cartSelections;
	}
}
