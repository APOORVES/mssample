package com.mssample.cart.exception;

import com.mssample.cart.common.CartProperties;

import lombok.Data;

@Data
public class CartException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private String errorCode;
	private String errorMessage;
	

	public CartException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public CartException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = CartProperties.getProperty(errorCode);
	}
}
