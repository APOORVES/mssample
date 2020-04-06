package com.mssample.cart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SELLER")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Seller {
	@Id
	@Column(name="SELLER_ID")
	private String selledId;
	@Column(name="SELLER_NAME")
	private String sellerName;
	@Column(name="SELLER_COUNT")
	private long sellerCount;

}
