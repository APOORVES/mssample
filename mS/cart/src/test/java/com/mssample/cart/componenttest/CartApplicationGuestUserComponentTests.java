package com.mssample.cart.componenttest;

import static org.hamcrest.CoreMatchers.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.jdbc.AutoConfigureDataJdbc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mssample.account.model.User;
import com.mssample.cart.extclient.AccountClient;
import com.mssample.cart.extclient.ProductClient;
import com.mssample.cart.model.Cart;
import com.mssample.cart.modelui.request.AddToCartRequestUI;
import com.mssample.cart.repo.CartRepository;
import com.mssample.cart.testdata.CartAllData;
import com.mssample.product.model.Product;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = CartController.class)
@ActiveProfiles(value = "test")
@AutoConfigureDataJdbc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartApplicationGuestUserComponentTests{

	private List<Product> products;
	private List<User> users;
	private List<Cart> carts;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountClient accountClient;
	@MockBean
	private ProductClient productClient;
	@MockBean
	private CartRepository cartRepository;
	
	@BeforeEach
	public void setUpData() {
		products = CartAllData.createProducts();
		users = CartAllData.createUsers();
		carts = CartAllData.createCarts();
	}
	
	@WithMockUser(value = "user")
	@Test
	public void testGuestUserAddtoCart() throws Exception {
		String productName1 = "Product1"; 
		String productCategory1 = "Category1";
		String productSeller1 = "SellerOne";
		String productName2 = "Product2"; 
		String productCategory2 = "Category2";
		String productSeller2 = "SellerTwo";
		AddToCartRequestUI addToCartRequestUI1 
			= new AddToCartRequestUI(productName1, productSeller1, 2, productCategory1);
		when(accountClient.findByName(users.get(0).getName())).thenReturn(Optional.of(users.get(0)));
		when(accountClient.findByName(users.get(1).getName())).thenReturn(Optional.of(users.get(1)));
		when(accountClient.findByName(users.get(2).getName())).thenReturn(Optional.of(users.get(2)));
		when(accountClient.findByName(users.get(3).getName())).thenReturn(Optional.of(users.get(3)));
		when(accountClient.findByName(users.get(4).getName())).thenReturn(Optional.of(users.get(4)));
		when(productClient.findByDisplayName(products.get(0).getDisplayName())).thenReturn(products.get(0));
		when(productClient.findByDisplayName(products.get(1).getDisplayName())).thenReturn(products.get(1));
		when(productClient.findByDisplayNameAndCategory(products.get(0).getDisplayName(), products.get(0).getCategory()))
			.thenReturn(products.get(0));
		when(productClient.findByDisplayNameAndCategory(products.get(1).getDisplayName(), products.get(1).getCategory()))
			.thenReturn(products.get(1));
		when(productClient.findByDisplayNameAndCategory(products.get(2).getDisplayName(), products.get(2).getCategory()))
			.thenReturn(products.get(2));

		//Guest
		User guestUser = new User(0, "guest123@guest.com", "Guest1234", "Pa$$0rd", "Pa$$0rd");
		when(accountClient.saveUser(any())).thenReturn(123456l);
		when(accountClient.saveUser(any())).thenReturn(123456l);
		when(cartRepository.saveAndFlush(any())).thenReturn(new Cart());
		AddToCartRequestUI addToCartRequestUI4 
		= new AddToCartRequestUI(productName2, productSeller2, 1, productCategory2);
		RequestBuilder requestBuilder10 = MockMvcRequestBuilders.post("/"+"guest"+"/addtocart")
				  .content(asJsonString(addToCartRequestUI4))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder10)
				.andDo(print())
				.andExpect(jsonPath("$.message", containsString("Cart has been successfully saved for the Guest User Id guest")));

		
	}

	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  

}
