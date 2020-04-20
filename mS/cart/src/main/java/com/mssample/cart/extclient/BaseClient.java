package com.mssample.cart.extclient;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import com.mssample.cart.aop.EnableLogging;

/**
 * Base Client for calling external mS via rest apis
 * @author Apoorve
 *
 */
public class BaseClient {
	@Value("${com.mssample.username}")
	private String userName; 
	@Value("${com.mssample.password}")
	private String password; 
	
	/**
	 * Create a httpo entity of type T
	 * @param <T>
	 * @param request
	 * @return
	 */
	@EnableLogging
	public <T> HttpEntity<T> createHttpEntity(T request) {
		HttpHeaders headers = createHttpHeaders();
        HttpEntity<T> entity = new HttpEntity<T>(request, headers);
        return entity;
	}
	
	/**
	 * create http headers with Basic auth credentials for calling other mS
	 * @return
	 */
	@EnableLogging
	public HttpHeaders createHttpHeaders()
	{
	    String notEncoded = userName + ":" + password;
	    String encodedAuth = "Basic " 
	    					+ Base64.getEncoder().encodeToString(notEncoded.getBytes());
	    HttpHeaders headers = new HttpHeaders();
	    headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.add("Authorization", encodedAuth);
	    return headers;
	}

}
