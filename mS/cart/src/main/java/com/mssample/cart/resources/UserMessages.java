package com.mssample.cart.resources;

import org.springframework.beans.factory.annotation.Value;

public class UserMessages {
	@Value("${com.mssample.cart.addToCartGuestUserSuccessMessage")
	public static String addToCartGuestUserSuccessMessage;
	@Value("${com.mssample.cart.addToCartRegisteredUserSuccessMessage")
	public static String addToCartRegisteredUserSuccessMessage;
	@Value("${com.mssample.cart.modifySuccessMessage")
	public static String modifySuccessMessage;
	@Value("${com.mssample.cart.cartNotFoundMessage")
	public static String cartNotFoundMessage;
	@Value("${com.mssample.cart.userNotFoundMessage")
	public static String userNotFoundMessage;
}
