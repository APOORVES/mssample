package com.mssample.account.exception;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class UserNotFoundExceptionTest {

	@Test
	void testUserNotFoundException() {
		UserNotFoundException unfe = new UserNotFoundException("UserNotFound");
		assertEquals("UserNotFound", unfe.getMessage());
	}

}
