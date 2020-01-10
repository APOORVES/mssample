package com.mssample.product.exception;

public class DealNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public DealNotFoundException(String message) {
        super(message);
    }
}
