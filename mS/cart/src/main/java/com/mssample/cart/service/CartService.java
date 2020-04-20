package com.mssample.cart.service;

import static com.mssample.cart.common.CartConstants.UI_ERROR_INVALID_USER;
import static com.mssample.cart.common.CartConstants.UI_ERROR_NO_EXISTING_CART;
import static com.mssample.cart.common.CartConstants.UI_ERROR_PRODUCT_NOT_PRESENT_IN_CART;
import static com.mssample.cart.common.CartConstants.UI_ERROR_TOO_MANY_ITEMS_OF_ONE_TYPE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mssample.account.model.User;
import com.mssample.cart.aop.EnableLogging;
import com.mssample.cart.exception.CartException;
import com.mssample.cart.extclient.AccountClient;
import com.mssample.cart.model.Cart;
import com.mssample.cart.model.CartItem;
import com.mssample.cart.modelui.request.ModifyCartRequestUI;
import com.mssample.cart.repo.CartRepository;
import com.mssample.product.model.Product;

/**
 * Cart Service  which implements most the business logic in cart
 * @author Apoorve
 *
 */
@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountClient accountClient;
	@Value("${com.mssample.cart.config.maxProductQuantity}")
	private String maxProductQuantity;

	/**
	 * Get cart for a given user name
	 * @param userName
	 * @return
	 */
	@EnableLogging
	public Cart getCart(String userName) {
		Optional<User> user = accountClient.findByName(userName);
		if(!user.isPresent())
			throw new CartException(UI_ERROR_INVALID_USER);
		Optional<Cart> cart = cartRepository.findByUserName(user.get().getName());
		if(!cart.isPresent()) {
			throw new CartException(UI_ERROR_NO_EXISTING_CART);
		}
		return cart.get();
	}

	/**
	 * Add to cart
	 * @param cart
	 */
	@EnableLogging
	public void addToCart(Cart cart) {
		validateCartItems(cart);
		cartRepository.saveAndFlush(cart);
	}

	/**
	 * Get cart count
	 * @param userName
	 * @return
	 */
	@EnableLogging
	public int getCartCount(String userName) {
		Cart cart = getCart(userName);
		return cart.getCartSelections().size();
	}

	/**
	 * Modify the cart
	 * @param cart
	 */
	@EnableLogging
	public void modifyCart(Cart cart) {
		validateCartItems(cart);
		cartRepository.saveAndFlush(cart);
	}

	/**
	 * Validate the cart
	 * @param cart
	 */
	@EnableLogging
	public void validateCartItems(Cart cart) {
		for(CartItem cartItem: cart.getCartSelections()) {
			if(cartItem.getQuantity() > Integer.parseInt(maxProductQuantity)) {
				throw new CartException(UI_ERROR_TOO_MANY_ITEMS_OF_ONE_TYPE);
			}
		}
	}
	
	/**
	 * Validate if a product is present in the cart
	 * @param modifyCartRequest
	 * @param cartToBeSaved
	 * @param product
	 */
	@EnableLogging
	public void validateProductPresentInCart(ModifyCartRequestUI modifyCartRequest
			, Cart cartToBeSaved, Product product) {
		boolean found = false;
		for(CartItem cartItem: cartToBeSaved.getCartSelections()) {
			if(cartItem.getProductDisplayName().equals(product.getDisplayName())) {
				cartItem.setQuantity(modifyCartRequest.getQuantity());
				cartItem.setOfferPrice(modifyCartRequest.getCartOfferPrice());
				found = true;
			}
		}
		if(!found) {
			throw new CartException(UI_ERROR_PRODUCT_NOT_PRESENT_IN_CART);
		}
	}

	/**
	 * Validate if a User is a valid user by calling Account mS
	 * @param userName
	 * @return
	 */
	@EnableLogging
	public Optional<User> validateUser(String userName) {
		Optional<User> user;
		user = accountClient.findByName(userName);
		if(!user.isPresent())
			throw new CartException(UI_ERROR_INVALID_USER);
		return user;
	}


}
