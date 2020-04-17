package com.mssample.cart.exception;

import com.mssample.cart.common.CartProperties;

import lombok.Getter;
import lombok.Setter;

/**
 * Class representing the business exception in Cart microservice 
 * @author Apoorve
 *
 */
@Getter @Setter
public class CartException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	/**
	 * Error Code
	 */
	private String errorCode;
	/**
	 * Error Message
	 */
	private String errorMessage;
	

	/**
	 * All Args Contructor
	 * @param errorCode
	 * @param errorMessage
	 */
	public CartException(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	/**
	 * Single Arg Contructor
	 * @param errorCode
	 */
	public CartException(String errorCode) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = CartProperties.getProperty(errorCode);
	}
}
