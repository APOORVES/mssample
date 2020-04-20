package com.mssample.cart.extclient;

import static com.mssample.cart.common.CartConstants.UI_ERROR_DURING_GUEST_CREATION;
import static com.mssample.cart.common.CartConstants.UI_ERROR_INVALID_USER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.mssample.account.model.User;
import com.mssample.cart.aop.EnableLogging;
import com.mssample.cart.exception.CartException;

/**
 * Client for calling external Account mS via rest apis
 * @author Apoorve
 *
 */
@Service
public class AccountClient extends BaseClient{
	@Autowired
	RestTemplate restTemplate;
	@Value("${com.mssample.account.url}")
	private String accountServiceBaseUrl; 
	/**
	 * Get User details by name
	 * @param userName
	 * @return
	 */
	@EnableLogging
	public Optional<User> findByName(String userName){
		User user;
		try {
			user = restTemplate.postForEntity(accountServiceBaseUrl+"/getUserDetails", 
					createHttpEntity(new User(userName)), User.class).getBody();
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CartException(UI_ERROR_INVALID_USER); 
		}
		return Optional.of(user);
	}
	
	/**
	 * Save user details (this is used in case of guest creation)
	 * @param user
	 * @return
	 */
	@EnableLogging
	public Long saveUser(User user){
		Long result;
		try {
			ResponseEntity<String> resultRE = restTemplate.postForEntity(accountServiceBaseUrl+"/signup", 
					createHttpEntity(user), String.class);
			result = Long.parseLong(resultRE.getBody());
		} catch (RestClientException e) {
			e.printStackTrace();
			throw new CartException(UI_ERROR_DURING_GUEST_CREATION); 
		}
		return result;
	}
}
