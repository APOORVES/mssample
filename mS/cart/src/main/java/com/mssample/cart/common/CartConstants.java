package com.mssample.cart.common;

/**
 * Class containing constants to be used in Cart application 
 * @author Apoorve
 * 
 */
public class CartConstants {
	public static final String GUEST_INDICATOR = "guest";
	public static final String GUEST_MARKER = "guest";
	
	//Error Codes
	public static final String UI_ERROR_INVALID_USER = "com.mssample.cart.inValidUser";
	public static final String UI_ERROR_DURING_GUEST_CREATION = "com.mssample.cart.guestCreationError";
	public static final String UI_ERROR_DURING_FETCH_OF_PRODUCT_DETAIL = "com.mssample.cart.productDetchError";
	public static final String UI_ERROR_TOO_MANY_ITEMS_OF_ONE_TYPE = "com.mssample.cart.tooManyItemsOfOneType";
	public static final String UI_ERROR_NO_EXISTING_CART = "com.mssample.cart.noExistingCart";
	public static final String UI_ERROR_PRODUCT_NOT_PRESENT_IN_CART = "com.mssample.cart.productNotPresentInCart";
	
	public static final String UI_ERROR_SYSTEM = "com.mssample.cart.cartsystemerror";
	
	public static final String UI_SUCCESS_GUEST_ADDTOCART_SUCCESSFUL = "com.mssample.cart.addToCartGuestUserSuccessMessage";
	public static final String UI_SUCCESS_USER_ADDTOCART_SUCCESSFUL = "com.mssample.cart.addToCartRegisteredUserSuccessMessage";
	public static final String UI_SUCCESS_MODIFY_SUCCESS_MESSAGE = "com.mssample.cart.modifySuccessMessage";

}
