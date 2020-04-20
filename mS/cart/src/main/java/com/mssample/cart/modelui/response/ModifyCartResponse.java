package com.mssample.cart.modelui.response;

import com.mssample.cart.common.CartProperties;

/**
 * Modify Cart Response to UI
 * @author Apoorve
 *
 */
public class ModifyCartResponse extends CartResponse {

	public ModifyCartResponse(String userId) {
		this.setMessage(CartProperties.getProperty("com.mssample.cart.modifySuccessMessage") 
				+ " " + userId);
	}

}
