package com.mssample.cart.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PURCHASE_HISTORY")
@Getter @Setter @NoArgsConstructor
@NamedQuery(name = "PurchaseHistory.findByName", query = "select distinct ph.product from PurchaseHistory ph join ph.user u where u.name =?1")
public class PurchaseHistory {
		@Id
		@Column(name="PURCHASE_ID")
		@GeneratedValue(strategy = GenerationType.AUTO)
		private long purchaseId;
		@JsonManagedReference
		@ManyToOne
	    @JoinColumn(name = "PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
		private Product product;
		@Column(name="PURCHASE_DATE")
		private Timestamp purchaseDate;
		@Column(name="QUANTITY")
		private long quantity;
		@Column(name="PRICE")
		private double price;
		@JsonManagedReference
		@ManyToOne
	    @JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")
		private User user;
}
