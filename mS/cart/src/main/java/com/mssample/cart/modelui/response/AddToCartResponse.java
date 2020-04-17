package com.mssample.cart.modelui.response;

import com.mssample.cart.model.Cart;
import com.mssample.cart.resources.UserMessages;
import static com.mssample.cart.common.CartConstants.*;

import com.mssample.cart.common.CartProperties;

public class AddToCartResponse extends CartResponse {
	public AddToCartResponse(Cart cart, UserMessages userMessages) {
		if(cart.getUserName().startsWith(GUEST_MARKER)) {
			this.setMessage(CartProperties.getProperty("com.mssample.cart.addToCartGuestUserSuccessMessage") + " " + cart.getUserName());
		}
		else{
			this.setMessage(CartProperties.getProperty("com.mssample.cart.addToCartRegisteredUserSuccessMessage") + " " + cart.getUserName());
		}
	}
}
