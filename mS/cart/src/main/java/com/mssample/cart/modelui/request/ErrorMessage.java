package com.mssample.cart.modelui.request;

import com.mssample.cart.common.CartProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ErrorMessage {
	private String errorCode;
	private String errorMessage;
	public ErrorMessage(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}
	public ErrorMessage(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = CartProperties.getProperty(errorCode);
	}
	
}
