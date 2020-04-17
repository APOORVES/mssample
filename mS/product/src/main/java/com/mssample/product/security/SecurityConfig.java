package com.mssample.product.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@Profile(value = {"default", "development", "production"})
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 
	@Autowired
	private MsSampleUserDetailsService userDetailsService;
	 
	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/","/searchproduct","/searchproduct/*","/deals","/deals/*","/details","/details/*","*/details/*","/*/details","/*/details/*","/fetchproduct","/fetchproduct/","/fetchproduct/*","/fetchproduct/*/","/fetchproduct/*/*","/fetchproduct/*/*/", "/fetchproduct/*/*/*", "/fetchproduct/*/*/*/").permitAll()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
        .formLogin().disable();
	}
	
	
	 
}
