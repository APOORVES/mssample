package com.mssample.cart.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mssample.cart.model.Cart;
import com.mssample.cart.modelui.request.AddToCartRequestUI;
import com.mssample.cart.modelui.request.ModifyCartRequestUI;
import com.mssample.cart.modelui.response.AddToCartResponse;
import com.mssample.cart.modelui.response.CartCountResponse;
import com.mssample.cart.modelui.response.CartResponse;
import com.mssample.cart.modelui.response.GetCartResponse;
import com.mssample.cart.modelui.response.ModifyCartResponse;
import com.mssample.cart.service.CartService;
import com.mssample.cart.service.CartTranslateService;

@RestController
public class CartController {
	@Autowired
	private CartService cartService;
	@Autowired
	private CartTranslateService cartTranslateService;
	@PostMapping(value = "/{userid}/addtocart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<AddToCartResponse> addToCart(@Valid @RequestBody AddToCartRequestUI addToCartRequest, @PathVariable("userid") String userName, Errors errors){
		Cart cart = cartTranslateService.translateCartAddToRequest(addToCartRequest, userName);
		cartService.addToCart(cart);
		ResponseEntity<AddToCartResponse> response = new ResponseEntity<AddToCartResponse>(new AddToCartResponse(cart), HttpStatus.OK);
		return response;
	}
	@PostMapping(value = "/{userid}/modifycart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ModifyCartResponse> modifyCart(@Valid @RequestBody ModifyCartRequestUI modifyCartRequest, @PathVariable("userid") String userName, Errors errors){
		Cart cart = cartTranslateService.translateCartModifyRequest(modifyCartRequest, userName);
		cartService.modifyCart(cart);
		ResponseEntity<ModifyCartResponse> response = new ResponseEntity<ModifyCartResponse>(new ModifyCartResponse(userName), HttpStatus.OK);
		return response;
	}
	@GetMapping(value = "/{userid}/cart", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartResponse> getCart(@Valid @PathVariable("userid") String userName, Errors errors){
		Cart cart = cartService.getCart(userName);
		GetCartResponse cartResponseUI = cartTranslateService.translateCart(cart);
		return new ResponseEntity<CartResponse>(cartResponseUI, HttpStatus.OK);
	}
	@GetMapping(value = "/{userid}/cartcount", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CartCountResponse> getCartCount(@Valid @PathVariable("userid") String userName, Errors errors){
		int cartCount = cartService.getCartCount(userName);
		ResponseEntity<CartCountResponse> response = new ResponseEntity<CartCountResponse>(new CartCountResponse(cartCount), HttpStatus.OK);
		return response;
	}

}
