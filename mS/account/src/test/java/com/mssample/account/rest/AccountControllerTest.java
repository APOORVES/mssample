package com.mssample.account.rest;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mssample.account.model.User;
import com.mssample.account.service.AccountService;
import com.mssample.account.testdata.AllData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = AccountController.class)
@ActiveProfiles(value = "test")
class AccountControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private AccountService accountService;
	private List<User> users;

	@BeforeEach
	public void populateAll(){
		users = AllData.createUsers();
	}

	@WithMockUser(value = "user")
	@Test
	void testLogin() throws Exception {
		Mockito.when(accountService.findUser(users.get(0))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
															  .content(asJsonString(users.get(0)))
															  .contentType(MediaType.APPLICATION_JSON)
															  .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
/*		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, new String("<Seller:"+users.get(0).getName()+">", HttpStatus.OK));
		byte[] data = out.toByteArray();
*/
		log.debug("expected="+new String("Seller:"+users.get(0).getName()));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String("Seller:"+users.get(0).getName()), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testSignup() throws Exception {
		Mockito.when(accountService.createUser(users.get(0))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
															  .content(asJsonString(users.get(0)))
															  .contentType(MediaType.APPLICATION_JSON)
															  .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
/*		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, new String("<Seller:"+users.get(0).getName()+">", HttpStatus.OK));
		byte[] data = out.toByteArray();
*/
		log.debug("expected="+new String("Seller:"+users.get(0).getName()));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String("User Successfully Registered"), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testSignupFailure() throws Exception {
		users.get(0).setPassword("password");
		users.get(0).setConfirmPassword("password");
		Mockito.when(accountService.createUser(users.get(0))).thenReturn(true);
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/signup")
															  .content(asJsonString(users.get(0)))
															  .contentType(MediaType.APPLICATION_JSON)
															  .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
/*		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, new String("<Seller:"+users.get(0).getName()+">", HttpStatus.OK));
		byte[] data = out.toByteArray();
*/
		log.debug("expected="+new String("Seller:"+users.get(0).getName()));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertTrue(response.getContentAsString().contains("400"));
		assertTrue(response.getContentAsString().contains("Password should contain at least an uppercase and a lowercase character, a number and a special character"));
		assertTrue(response.getContentAsString().contains("Confirm Password should contain at least an uppercase and a lowercase character, a number and a special character"));
	}

	@WithMockUser(value = "user")
	@Test
	void testAccountUpdate() throws Exception {
		Mockito.when(accountService.updateUser(users.get(0))).thenReturn(true);
		users.get(0).setEmail("userone@domain.com");
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/"+users.get(0).getName()+"/update")
															  .content(asJsonString(users.get(0)))
															  .contentType(MediaType.APPLICATION_JSON)
															  .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
/*		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, new String("<Seller:"+users.get(0).getName()+">", HttpStatus.OK));
		byte[] data = out.toByteArray();
*/
		log.debug("expected="+new String("Seller:"+users.get(0).getName()));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String("Successfully Updated"), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testGetUserDetails() throws Exception {
		Mockito.when(accountService.getUser(any(User.class))).thenReturn(users.get(0));
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/getUserDetails")
															  .content(asJsonString(users.get(0)))
															  .contentType(MediaType.APPLICATION_JSON)
															  .accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		log.debug("expected="+new String("Seller:"+users.get(0).getName()));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertTrue(response.getContentAsString().contains(users.get(0).getEmail()));
		assertTrue(response.getContentAsString().contains(users.get(0).getName()));
		assertTrue(response.getContentAsString().contains(users.get(0).getPassword()));
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
