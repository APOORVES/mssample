package com.mssample.cart.componenttest;

import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
import com.mssample.cart.modelui.request.ModifyCartRequestUI;
import com.mssample.cart.testdata.CartAllData;
import com.mssample.product.model.Product;

//@ExtendWith(SpringExtension.class)
//@WebMvcTest(value = CartController.class)
@ActiveProfiles(value = "test")
@AutoConfigureDataJdbc
@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CartApplicationComponentTests{

	private List<Product> products;
	private List<User> users;
	private List<Cart> carts;
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountClient accountClient;
	@MockBean
	private ProductClient productClient;
	
	@BeforeEach
	public void setUpData() {
		products = CartAllData.createProducts();
		users = CartAllData.createUsers();
		carts = CartAllData.createCarts();
	}
	
	@WithMockUser(value = "user")
	@Test
	public void testAddToCart1() throws Exception {
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
		//addToCart
		RequestBuilder requestBuilder1 = MockMvcRequestBuilders.post("/"+users.get(0).getName()+"/addtocart")
				  .content(asJsonString(addToCartRequestUI1))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder1)
				.andDo(print())
				.andExpect(jsonPath("$.message", is("Cart has been successfully saved for the User Id " +users.get(0).getName())))
				.andExpect(status().is2xxSuccessful());
		//getCart
		RequestBuilder requestBuilder2 = MockMvcRequestBuilders.get("/"+users.get(0).getName()+"/cart")
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder2)
				.andDo(print())
				.andExpect(jsonPath("$.totalPrice", is(16.0)))
				.andExpect(jsonPath("$.totalDeliveryCharge", is(4.0)))
				.andExpect(jsonPath("$.grandTotal", is(20.0)))
				.andExpect(jsonPath("$.cartDetails[0].displayName", is("Product1")))
				.andExpect(jsonPath("$.cartDetails[0].sellerName", is("SellerOne")))
				.andExpect(jsonPath("$.cartDetails[0].price", is(10.0)))
				.andExpect(jsonPath("$.cartDetails[0].deliveryCharge", is(2.0)))
				.andExpect(jsonPath("$.cartDetails[0].quantity", is(2)))
				.andExpect(jsonPath("$.cartDetails[0].total", is(10.0)))
				.andExpect(jsonPath("$.cartDetails[0].cartOfferPrice", is(8.0)))
				.andExpect(status().is2xxSuccessful());
		//addToCart
		AddToCartRequestUI addToCartRequestUI2 
		= new AddToCartRequestUI(productName2, productSeller2, 2, productCategory2);
		RequestBuilder requestBuilder3 = MockMvcRequestBuilders.post("/"+users.get(0).getName()+"/addtocart")
				  .content(asJsonString(addToCartRequestUI2))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder3)
				.andDo(print())
				.andExpect(jsonPath("$.message", is("Cart has been successfully saved for the User Id " +users.get(0).getName())))
				.andExpect(status().is2xxSuccessful());
		
		//cartCount
		RequestBuilder requestBuilder4 = MockMvcRequestBuilders.get("/"+users.get(0).getName()+"/cartcount")
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder4)
				.andDo(print())
				.andExpect(jsonPath("$.cartCount", is(2)))
				.andExpect(status().is2xxSuccessful());

		ModifyCartRequestUI modifyCartRequestUI1 
		= new ModifyCartRequestUI(productName2, productSeller2, 3, 10);
		//modifyCart
		RequestBuilder requestBuilder5 = MockMvcRequestBuilders.post("/"+users.get(0).getName()+"/modifycart")
				  .content(asJsonString(modifyCartRequestUI1))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder5)
				.andDo(print())
				.andExpect(jsonPath("$.message", is("Cart has been Successfully modified "+users.get(0).getName())))
				.andExpect(status().is2xxSuccessful());
		//getCart
		RequestBuilder requestBuilder6 = MockMvcRequestBuilders.get("/"+users.get(0).getName()+"/cart")
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder6)
				.andDo(print())
				.andExpect(jsonPath("$.totalPrice", is(46.0)))
				.andExpect(jsonPath("$.totalDeliveryCharge", is(13.0)))
				.andExpect(jsonPath("$.grandTotal", is(59.0)))
				.andExpect(jsonPath("$.cartDetails[0].displayName", is("Product1")))
				.andExpect(jsonPath("$.cartDetails[0].sellerName", is("SellerOne")))
				.andExpect(jsonPath("$.cartDetails[0].price", is(10.0)))
				.andExpect(jsonPath("$.cartDetails[0].deliveryCharge", is(2.0)))
				.andExpect(jsonPath("$.cartDetails[0].quantity", is(2)))
				.andExpect(jsonPath("$.cartDetails[0].total", is(10.0)))
				.andExpect(jsonPath("$.cartDetails[0].cartOfferPrice", is(8.0)))
				.andExpect(jsonPath("$.cartDetails[1].displayName", is("Product2")))
				.andExpect(jsonPath("$.cartDetails[1].sellerName", is("SellerTwo")))
				.andExpect(jsonPath("$.cartDetails[1].price", is(20.0)))
				.andExpect(jsonPath("$.cartDetails[1].deliveryCharge", is(3.0)))
				.andExpect(jsonPath("$.cartDetails[1].quantity", is(3)))
				.andExpect(jsonPath("$.cartDetails[1].total", is(13.0)))
				.andExpect(jsonPath("$.cartDetails[1].cartOfferPrice", is(10.0)))
				.andExpect(status().is2xxSuccessful());
		
		//Validations:
		ModifyCartRequestUI modifyCartRequestUI2 
		= new ModifyCartRequestUI(productName2, productSeller2, 6, 10);
		RequestBuilder requestBuilder7 = MockMvcRequestBuilders.post("/"+users.get(0).getName()+"/modifycart")
				  .content(asJsonString(modifyCartRequestUI2))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder7)
				.andDo(print())
				.andExpect(jsonPath("$.[0].errorCode", is("quantity")))
				.andExpect(jsonPath("$.[0].errorMessage", is("Maximum allowed quantity for any item is 4")))
				.andExpect(status().isBadRequest());

		AddToCartRequestUI addToCartRequestUI3 
		= new AddToCartRequestUI(productName2, productSeller2, 1, productCategory2);
		RequestBuilder requestBuilder8 = MockMvcRequestBuilders.post("/"+"NonExistantUser"+"/addtocart")
				  .content(asJsonString(addToCartRequestUI3))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder8)
				.andDo(print())
				.andExpect(jsonPath("$.errorCode", is("com.mssample.cart.inValidUser")))
				.andExpect(jsonPath("$.errorMessage", is("The User name you mentioned is not correct")))
				.andExpect(status().isBadRequest());

		ModifyCartRequestUI modifyCartRequestUI3 
		= new ModifyCartRequestUI(productName2, productSeller2, 2, 10);
		RequestBuilder requestBuilder9 = MockMvcRequestBuilders.post("/"+users.get(2).getName()+"/modifycart")
				  .content(asJsonString(modifyCartRequestUI3))
				  .contentType(MediaType.APPLICATION_JSON)
				  .accept(MediaType.APPLICATION_JSON);
		mockMvc.perform(requestBuilder9)
				.andDo(print())
				.andExpect(jsonPath("$.errorCode", is("com.mssample.cart.noExistingCart")))
				.andExpect(jsonPath("$.errorMessage", is("No existing cart for the given user name")))
				.andExpect(status().isBadRequest());

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
