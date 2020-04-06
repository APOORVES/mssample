package com.mssample.cart.service;

import static com.mssample.cart.common.CartConstants.GUEST_INDICATOR;
import static com.mssample.cart.common.CartConstants.GUEST_MARKER;
import static com.mssample.cart.common.CartConstants.UI_ERROR_INVALID_USER;
import static com.mssample.cart.common.CartConstants.UI_ERROR_PRODUCT_NOT_PRESENT_IN_CART;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import com.mssample.cart.exception.CartException;
import com.mssample.cart.model.Cart;
import com.mssample.cart.model.CartItem;
import com.mssample.cart.model.Product;
import com.mssample.cart.model.User;
import com.mssample.cart.modelui.request.AddToCartRequestUI;
import com.mssample.cart.modelui.request.ModifyCartRequestUI;
import com.mssample.cart.modelui.response.CartDetail;
import com.mssample.cart.modelui.response.GetCartResponse;
import com.mssample.cart.repo.AccountRepository;
import com.mssample.cart.repo.CartRepository;
import com.mssample.cart.repo.ProductRepository;

public class CartTranslateService {
	
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartService cartService;

	public GetCartResponse translateCart(Cart cart) {
		List<CartDetail> cartDetails = cart.getCartSelections().stream().map(ci->{
			return new CartDetail(ci.getProduct().getDisplayName()
					, ci.getProduct().getCategory(), ci.getProduct().getSeller().getSellerName()
					, ci.getProduct().getPrice(), ci.getProduct().getDeliveryCharge()
					, ci.getQuantity(), ci.getProduct().getTotal(), ci.getProduct().getOfferPrice());
		}).collect(Collectors.toList());
		GetCartResponse getCartResponse = new GetCartResponse(cartDetails);
		return getCartResponse;
	}

	public Cart translateCartAddToRequest(@Valid AddToCartRequestUI addToCartRequest, String userName) {
		Optional<User> user;
		if(GUEST_INDICATOR.equals(userName)) {
			UUID uniqueID = UUID.randomUUID();
			User userToBeCreated = new User();
			userToBeCreated.setEmail(GUEST_MARKER + uniqueID + "@"+GUEST_MARKER+".com");
			userToBeCreated.setName(GUEST_MARKER + uniqueID);
			userToBeCreated.setPassword(uniqueID.toString());
			userToBeCreated.setConfirmPassword(uniqueID.toString());
			accountRepository.saveAndFlush(userToBeCreated);
			user = Optional.of(userToBeCreated);
		}else {
			user = accountRepository.findByName(userName);
			if(!user.isPresent())
				throw new CartException(UI_ERROR_INVALID_USER);
		}
		List<Product> products = productRepository.findByDisplayNameAndCategory(addToCartRequest.getProductName(), addToCartRequest.getCategory());
		Optional<Cart> existingCart = cartRepository.findByUserId(user.get().getUserId());
		Cart cartToBeSaved;
		if(existingCart.isPresent()) {
			cartToBeSaved = existingCart.get();
		}else {
			List<CartItem> cartSelections = new ArrayList<>();
			cartToBeSaved = new Cart(user.get(), cartSelections);
		}
		boolean found = false;
		for(CartItem cartItem: cartToBeSaved.getCartSelections()) {
			if(cartItem.getProduct().getProductId().equals(products.get(0).getProductId())) {
				cartItem.setQuantity(cartItem.getQuantity()+addToCartRequest.getQuantity());
				found = true;
			}
		}
		if(!found) {
			cartToBeSaved.getCartSelections().add(new CartItem(products.get(0).getProductId(), products.get(0), addToCartRequest.getQuantity(), products.get(0).getOfferPrice()));
		}
		return cartToBeSaved;
	}

	public Cart translateCartModifyRequest(@Valid ModifyCartRequestUI modifyCartRequest, String userName) {
		Cart cartToBeSaved = cartService.getCart(userName);
		List<Product> products = productRepository.findByDisplayName(modifyCartRequest.getProductName());
		boolean found = false;
		for(CartItem cartItem: cartToBeSaved.getCartSelections()) {
			if(cartItem.getProduct().getProductId().equals(products.get(0).getProductId())) {
				cartItem.setQuantity(modifyCartRequest.getQuantity());
				cartItem.setOfferPrice(modifyCartRequest.getCartOfferPrice());
				found = true;
			}
		}
		if(!found) {
			throw new CartException(UI_ERROR_PRODUCT_NOT_PRESENT_IN_CART);
		}
		return cartToBeSaved;
	}

}
