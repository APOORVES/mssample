package com.mssample.cart.modelui.response;

import com.mssample.cart.model.Cart;
import com.mssample.cart.resources.UserMessages;
import static com.mssample.cart.common.CartConstants.*;

public class AddToCartResponse extends CartResponse {
	public AddToCartResponse(Cart cart) {
		if(cart.getUser().getName().startsWith(GUEST_MARKER)) {
			this.setMessage(UserMessages.addToCartGuestUserSuccessMessage + " " + cart.getUser().getUserId());
		}
		else{
			this.setMessage(UserMessages.addToCartRegisteredUserSuccessMessage + " " + cart.getUser().getUserId());
		}
	}
}
