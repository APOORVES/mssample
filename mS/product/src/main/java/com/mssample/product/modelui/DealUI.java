package com.mssample.product.modelui;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class DealUI {
	private String displayName;
	private String shortDesc;
	private String category;
	private String discount;

}
