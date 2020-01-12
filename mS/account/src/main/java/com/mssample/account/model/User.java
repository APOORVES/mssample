package com.mssample.account.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "USER")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="USER_ID")
	@JsonIgnore
	private long userId;
	@NotNull(message = "Email cannot be empty")
	@Email(message = "Email id is not Valid")
	@Column(name="EMAIL")
	private String email;
	@Pattern(regexp = "[a-zA-Z_]+", message = "Name cannot have numbers or special characters")
	@NotNull(message = "User Name cannot be empty")
	@Column(name="USER_NAME")
	private String name;
	@NotNull(message = "Password cannot be empty")
	@Column(name="PASSWORD")
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "Password should contain at least an uppercase and a lowercase character, a number and a special character")
	private String password;
	@NotNull(message = "Confirm Password cannot be empty")
	@Transient
	@Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}$", message = "Confirm Password should contain at least an uppercase and a lowercase character, a number and a special character")
	private String confirmPassword;
}
