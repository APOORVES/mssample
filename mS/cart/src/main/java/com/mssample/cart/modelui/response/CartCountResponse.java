package com.mssample.cart.modelui.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class CartCountResponse extends CartResponse {
	private int cartCount;
}
