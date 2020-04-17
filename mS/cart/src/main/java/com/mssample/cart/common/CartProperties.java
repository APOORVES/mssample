package com.mssample.cart.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Class to fetch property values
 * @author Apoorve
 *
 */
@Component
public class CartProperties {
	private static Environment env;
	
	@Autowired
	public CartProperties(Environment env) {
		CartProperties.env = env;
	}
	/** 
	 * @param key String representing the key of the property 
	 * @return String representing the value of the property
	 */
	public static String getProperty(String key) {
		return env.getProperty(key);
	}
}
