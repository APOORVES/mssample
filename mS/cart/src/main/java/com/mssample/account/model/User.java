package com.mssample.account.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
	public User(String userName) {
		this.name=userName;
	}
	/**
	 * System generated user id
	 */
	@JsonIgnore
	private long userId;
	/**
	 * User Email
	 */
	private String email;
	/**
	 * User name that is used for login
	 */
	private String name;
	/**
	 * User Password
	 */
	private String password;
	/**
	 * User confirm Password field
	 */
	private String confirmPassword;
}
