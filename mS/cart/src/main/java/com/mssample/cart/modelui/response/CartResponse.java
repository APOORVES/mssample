package com.mssample.cart.modelui.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Base response for Cart
 * @author Apoorve
 *
 */
@Getter @Setter @AllArgsConstructor @NoArgsConstructor
public class CartResponse {
	private String message;
}
