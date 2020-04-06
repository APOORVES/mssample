package com.mssample.cart.modelui.response;

import com.mssample.cart.resources.UserMessages;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class ModifyCartResponse extends CartResponse {

	public ModifyCartResponse(String userId) {
		this.setMessage(UserMessages.modifySuccessMessage + " " + userId);
	}

}
