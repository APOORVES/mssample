package com.mssample.cart.modelui.response;

import static com.mssample.cart.common.CartConstants.GUEST_MARKER;

import com.mssample.cart.common.CartProperties;
import com.mssample.cart.model.Cart;

/**
 * Add to Cart respose to UI
 * @author Apoorve
 *
 */
public class AddToCartResponse extends CartResponse {
	public AddToCartResponse(Cart cart) {
		if(cart.getUserName().startsWith(GUEST_MARKER)) {
			this.setMessage(CartProperties.getProperty("com.mssample.cart.addToCartGuestUserSuccessMessage") + " " + cart.getUserName());
		}
		else{
			this.setMessage(CartProperties.getProperty("com.mssample.cart.addToCartRegisteredUserSuccessMessage") + " " + cart.getUserName());
		}
	}
}
