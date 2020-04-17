package com.mssample.cart.service;

import static com.mssample.cart.common.CartConstants.UI_ERROR_INVALID_USER;
import static com.mssample.cart.common.CartConstants.UI_ERROR_NO_EXISTING_CART;
import static com.mssample.cart.common.CartConstants.UI_ERROR_TOO_MANY_ITEMS_OF_ONE_TYPE;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mssample.account.model.User;
import com.mssample.cart.exception.CartException;
import com.mssample.cart.extclient.AccountClient;
import com.mssample.cart.model.Cart;
import com.mssample.cart.model.CartItem;
import com.mssample.cart.repo.CartRepository;

@Service
public class CartService {

	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private AccountClient accountClient;
	@Value("${com.mssample.cart.config.maxProductQuantity}")
	private String maxProductQuantity;

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

	public void addToCart(Cart cart) {
		validateCartItems(cart);
		cartRepository.saveAndFlush(cart);
	}

	public int getCartCount(String userName) {
		Cart cart = getCart(userName);
		return cart.getCartSelections().size();
	}

	public void modifyCart(Cart cart) {
		validateCartItems(cart);
		cartRepository.saveAndFlush(cart);
	}

	public void validateCartItems(Cart cart) {
		for(CartItem cartItem: cart.getCartSelections()) {
			if(cartItem.getQuantity() > Integer.parseInt(maxProductQuantity)) {
				throw new CartException(UI_ERROR_TOO_MANY_ITEMS_OF_ONE_TYPE);
			}
		}
	}

}
