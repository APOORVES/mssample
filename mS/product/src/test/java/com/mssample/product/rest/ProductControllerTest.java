package com.mssample.product.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
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
import com.mssample.product.model.Product;
import com.mssample.product.rest.transform.ProductTransformer;
import com.mssample.product.security.SecurityConfig;
import com.mssample.product.service.ProductService;
import com.mssample.product.testdata.AllData;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RunWith(SpringRunner.class)
@WebMvcTest(value = ProductController.class)
@ActiveProfiles(value = "test")
@Import(SecurityConfig.class)
class ProductControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	@MockBean
	private ProductService productService;
	@MockBean
	private ProductTransformer productTransformer;
	private List<Product> products;
	
	@BeforeEach
	public void populateAll() {
		products = AllData.createProducts();
	}
	
	@WithMockUser(value = "user")
	@Test
	void testSearchProduct() throws Exception {
		List<Product> resultList = new ArrayList<Product>();
		resultList.add(products.get(0));
		Mockito.when(productService.searchProduct(products.get(0).getDisplayName())).thenReturn(resultList);
		Mockito.when(productTransformer.transformProducts(resultList)).thenCallRealMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/searchproduct/"+products.get(0).getDisplayName()).accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, productTransformer.transformProducts(resultList));
		byte[] data = out.toByteArray();
		log.debug("new String(data)="+new String(data));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String(data), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testGetDeals() throws Exception {
		List<Product> resultList = new ArrayList<Product>();
		resultList.add(products.get(0));
		Mockito.when(productService.getDeals()).thenReturn(resultList);
		Mockito.when(productTransformer.transformDeals(resultList)).thenCallRealMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/deals").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, productTransformer.transformDeals(resultList));
		byte[] data = out.toByteArray();
		log.debug("new String(data)="+new String(data));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String(data), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testProductDetail() throws Exception {
		List<Product> resultList = new ArrayList<Product>();
		resultList.add(products.get(0));
		Mockito.when(productService.searchProductExact(products.get(0).getDisplayName())).thenReturn(resultList);
		Mockito.when(productTransformer.transformProductsDetails(resultList)).thenCallRealMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/"+products.get(0).getDisplayName()+"/details").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, productTransformer.transformProductsDetails(resultList));
		byte[] data = out.toByteArray();
		log.debug("new String(data)="+new String(data));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String(data), response.getContentAsString());
	}

	@WithMockUser(value = "user")
	@Test
	void testUserRecommendations() throws Exception {
		List<Product> resultList = new ArrayList<Product>();
		resultList.add(products.get(0));
		Mockito.when(productService.getRecommendations("testuser")).thenReturn(resultList);
		Mockito.when(productTransformer.transformRecommendations(resultList)).thenCallRealMethod();
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/testuser/recommendations").accept(MediaType.APPLICATION_JSON);
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		MockHttpServletResponse response = result.getResponse();
		ObjectMapper mapper = new ObjectMapper();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		mapper.writeValue(out, productTransformer.transformRecommendations(resultList));
		byte[] data = out.toByteArray();
		log.debug("new String(data)="+new String(data));
		log.debug("response.getContentAsString()="+response.getContentAsString());
		assertEquals(new String(data), response.getContentAsString());
	}


}
