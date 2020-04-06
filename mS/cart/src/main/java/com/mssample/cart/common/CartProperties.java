package com.mssample.cart.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class CartProperties {
	private static Environment env;
	
	@Autowired
	public CartProperties(Environment env) {
		CartProperties.env = env;
	}
	public static String getProperty(String key) {
		return env.getProperty(key);
	}
}
