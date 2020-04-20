package com.mssample.cart.extclient;

import static com.mssample.cart.common.CartConstants.UI_ERROR_DURING_FETCH_OF_PRODUCT_DETAIL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mssample.cart.aop.EnableLogging;
import com.mssample.cart.exception.CartException;
import com.mssample.product.model.Product;

/**
 * Client for calling external product mS via rest apis
 * @author Apoorve
 *
 */
@Service
@SuppressWarnings("unchecked")
public class ProductClient extends BaseClient{
	@Autowired
	RestTemplate restTemplate;
	@Value("${com.mssample.product.url}")
	private String productServiceBaseUrl;
	/**
	 * Find Product using display name by cal;ling product microservice
	 * @param displayName
	 * @return
	 */
	@EnableLogging
	public Product findByDisplayName(String displayName){
		Product result;
		try {
			result = restTemplate.exchange(productServiceBaseUrl+"/fetchproduct/"+displayName
					, HttpMethod.GET
					, new HttpEntity<Object>(createHttpHeaders()), Product.class).getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CartException(UI_ERROR_DURING_FETCH_OF_PRODUCT_DETAIL);
		}
		return result;
	}
	/**
	 * Find product by displya name and category by calling product microservice
	 * @param displayName
	 * @param category
	 * @return
	 */
	@EnableLogging
	public Product findByDisplayNameAndCategory(String displayName, String category){
		Product result;
		try {
			ResponseEntity re = restTemplate.exchange(productServiceBaseUrl+"/fetchproduct/"
								+ category + "/" + displayName 
								, HttpMethod.GET, new HttpEntity<Object>(createHttpHeaders())
								, Product.class);
			result = (Product) re.getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CartException(UI_ERROR_DURING_FETCH_OF_PRODUCT_DETAIL);
		}
		return result;
	}

}
