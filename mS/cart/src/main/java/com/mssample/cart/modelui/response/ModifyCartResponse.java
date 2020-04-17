package com.mssample.cart.modelui.response;

import com.mssample.cart.common.CartProperties;
import com.mssample.cart.resources.UserMessages;

public class ModifyCartResponse extends CartResponse {

	public ModifyCartResponse(String userId, UserMessages userMessages) {
		this.setMessage(CartProperties.getProperty("com.mssample.cart.modifySuccessMessage") 
				+ " " + userId);
	}

}
