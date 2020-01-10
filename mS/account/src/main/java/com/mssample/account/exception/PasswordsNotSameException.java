package com.mssample.account.exception;

public class PasswordsNotSameException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public PasswordsNotSameException(String message) {
        super(message);
    }
}
