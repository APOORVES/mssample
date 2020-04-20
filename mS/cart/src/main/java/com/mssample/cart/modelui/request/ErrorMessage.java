package com.mssample.cart.modelui.request;

import com.mssample.cart.common.CartProperties;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Error message class
 * @author Apoorve
 *
 */
@Getter @Setter @NoArgsConstructor
public class ErrorMessage {
	/**
	 * Error code
	 */
	private String errorCode;
	/**
	 * Error Message
	 */
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
