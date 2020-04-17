package com.mssample.product.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Seller {
	private String selledId;
	private String sellerName;
	private long sellerCount;

}
