package com.mssample.cart.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@Order(SecurityProperties.BASIC_AUTH_ORDER)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	 
    @Value("${com.mssample.username}")
    private String userName;
    @Value("${com.mssample.password}")
    private String password;

	/**
	 * Security auth config
	 * @param auth
	 * @throws Exception
	 */
	@Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
          .withUser(userName).password(passwordEncoder().encode(password))
          .authorities("USER");
    }
	
	/**
	 * Secuitry configuration
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
        .antMatchers("/","/addtocart","/addtocart/*","*/addtocart/*","/*/addtocart",
        		"/modifycart","/*/modifycart","/*/modifycart/*","/*/modifycart",
        		"/cart","/cart/*","/*/cart","/*/cart/*",
        		"/cartcount","/cartcount/*","/*/cartcount","/*/cartcount/*").authenticated()
        .anyRequest().authenticated()
        .and()
        .httpBasic()
        .and()
        .csrf().disable()
        .formLogin().disable();
	}
	
	/**
	 * @return password encoder bean
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}
