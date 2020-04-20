package com.mssample.cart.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model class representing Cart data model
 * @author Apoorve
 *
 */
@Entity
@Table(name = "CART")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
@SequenceGenerator(name="cart_seq", initialValue=1, allocationSize=100)
public class Cart {
	/**
	 * Cart ID 
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "cart_seq")
	@Column(name="CART_ID")
	private Long cartId;
	/**
	 * Cart user name
	 */
	@Column(name="USER_NAME")
	private String userName;
	/**
	 * cart Items
	 */
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	private List<CartItem> cartSelections;
	/**
	 * @param userName
	 * @param cartSelections
	 */
	public Cart(String userName, List<CartItem> cartSelections) {
		super();
		this.cartId = null;
		this.userName = userName;
		this.cartSelections = cartSelections;
	}
}
