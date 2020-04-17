package com.mssample.cart.resources;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UserMessages {
	@Value("${com.mssample.cart.cartNotFoundMessage}")
	public String cartNotFoundMessage;
	@Value("${com.mssample.cart.userNotFoundMessage}")
	public String userNotFoundMessage;
}
