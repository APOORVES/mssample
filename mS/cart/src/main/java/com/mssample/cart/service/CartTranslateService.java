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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mssample.account.model.User;
import com.mssample.cart.exception.CartException;
import com.mssample.cart.extclient.AccountClient;
import com.mssample.cart.extclient.ProductClient;
import com.mssample.cart.model.Cart;
import com.mssample.cart.model.CartItem;
import com.mssample.cart.modelui.request.AddToCartRequestUI;
import com.mssample.cart.modelui.request.ModifyCartRequestUI;
import com.mssample.cart.modelui.response.CartDetail;
import com.mssample.cart.modelui.response.GetCartResponse;
import com.mssample.cart.repo.CartRepository;
import com.mssample.product.model.Product;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter @Setter
public class CartTranslateService {
	
	@Autowired
	private AccountClient accountClient;
	@Autowired
	private ProductClient productClient;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private CartService cartService;
	@Value("${com.mssample.cart.config.freedeliveryThreshold}")
	private String freeDeliveryThreshold;

	public GetCartResponse translateCart(Cart cart) {
		List<CartDetail> cartDetails = cart.getCartSelections().stream().map(ci->{
			Product product = productClient.findByDisplayName(ci.getProductDisplayName());
			return new CartDetail(product.getDisplayName()
					, product.getCategory(), product.getSeller().getSellerName()
					, product.getPrice(), product.getDeliveryCharge()
					, ci.getQuantity(), ci.getOfferPrice()+product.getDeliveryCharge(), ci.getOfferPrice());
		}).collect(Collectors.toList());
		GetCartResponse getCartResponse = new GetCartResponse(cartDetails, Double.parseDouble(freeDeliveryThreshold));
		return getCartResponse;
	}

	public Cart translateAddToCartRequest(@Valid AddToCartRequestUI addToCartRequest, String userName) {
		Optional<User> user;
		if(GUEST_INDICATOR.equals(userName)) {
			UUID uniqueID = UUID.randomUUID();
			String uniqueIDStr = uniqueID.toString().replace("-", "");
			User userToBeCreated = new User();
			userToBeCreated.setEmail(GUEST_MARKER + uniqueIDStr + "@"+GUEST_MARKER+".com");
			userToBeCreated.setName(GUEST_MARKER+ uniqueIDStr);
			userToBeCreated.setPassword(uniqueIDStr+"Aa@1");//Extra string to make password complaint
			userToBeCreated.setConfirmPassword(uniqueIDStr+"Aa@1");//Extra string to make password complaint
			Long userId = accountClient.saveUser(userToBeCreated);
			userToBeCreated.setUserId(userId);
			user = Optional.of(userToBeCreated);
		}else {
			user = accountClient.findByName(userName);
			if(!user.isPresent())
				throw new CartException(UI_ERROR_INVALID_USER);
		}
		Product product = productClient.findByDisplayNameAndCategory(addToCartRequest.getProductName(), addToCartRequest.getCategory());
		Optional<Cart> existingCart = cartRepository.findByUserName(user.get().getName());
		Cart cartToBeSaved;
		if(existingCart.isPresent()) {
			cartToBeSaved = existingCart.get();
		}else {
			List<CartItem> cartSelections = new ArrayList<>();
			cartToBeSaved = new Cart(user.get().getName(), cartSelections);
		}
		boolean found = false;
		for(CartItem cartItem: cartToBeSaved.getCartSelections()) {
			if(cartItem.getProductDisplayName().equals(product.getDisplayName())) {
				cartItem.setQuantity(cartItem.getQuantity()+addToCartRequest.getQuantity());
				found = true;
			}
		}
		if(!found) {
			cartToBeSaved.getCartSelections().add(new CartItem(product.getDisplayName(), addToCartRequest.getQuantity(), product.getOfferPrice(), cartToBeSaved));
		}
		return cartToBeSaved;
	}

	public Cart translateCartModifyRequest(@Valid ModifyCartRequestUI modifyCartRequest, String userName) {
		Cart cartToBeSaved = cartService.getCart(userName);
		Product product = productClient.findByDisplayName(modifyCartRequest.getProductName());
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
		return cartToBeSaved;
	}
	

}
