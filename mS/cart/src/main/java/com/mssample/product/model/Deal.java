package com.mssample.product.model;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Deal {
	private String dealId;
	private String dealName;
	private Product product;
	private Timestamp startDate;
	private Timestamp endDate;
	private double discount;
	


}
