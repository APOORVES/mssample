package com.mssample.account.rest;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.mssample.account.exception.PasswordsNotSameException;
import com.mssample.account.model.ErrorMessage;
import com.mssample.account.model.User;
import com.mssample.account.service.AccountService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class AccountController {
	@Autowired
	AccountService accountService;
	
	@PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> login(@RequestBody User user){
		log.info("login invoked for userName="+user.getName());
		Boolean result = accountService.findUser(user); 
		log.info("login successfull for userName="+user.getName());
		ResponseEntity<String> response = new ResponseEntity<String>("Seller:"+user.getName(), HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity signup(@Valid @RequestBody User user, Errors errors){
		log.info("signup invoked for user = "+user.getName());
		boolean result;
		if (errors.hasErrors()) {
			String response = "";
			response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			ErrorMessage error = new ErrorMessage();
			error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
			error.setErrorMessage(response);
			return ResponseEntity.ok(error);
		}
		else
		{
			if(!user.getPassword().equals(user.getConfirmPassword()))
			{
				throw new PasswordsNotSameException("password and confirm password not same.");
			}
			result = accountService.createUser(user);
		}
		log.info("signup created account result="+result);
		ResponseEntity<String> responseHttp = new ResponseEntity<String>("User Successfully Registered", HttpStatus.OK);
		log.info("signup returning response="+responseHttp);
		return responseHttp;
	}

	@PostMapping(value = "/{username}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity accountUpdate(@Valid @RequestBody User user, @PathVariable("username") String username, Errors errors){
		log.info("accountUpdate for username="+username);
		user.setName(username);
		if (errors.hasErrors()) {
			String response = "";
			response = errors.getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(","));
			ErrorMessage error = new ErrorMessage();
			error.setErrorCode(HttpStatus.NOT_ACCEPTABLE.value());
			error.setErrorMessage(response);
			return ResponseEntity.ok(error);
		}
		boolean result = accountService.updateUser(user); 
		log.info("sucessfully udpated the account for username="+user.getName() + "result=" + result);
		ResponseEntity<String> response = new ResponseEntity<String>("Successfully Updated", HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/getUserDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<User> getUserDetails(@RequestBody User user){
		log.info("getUserDetails invoked for userName="+user.getName());
		User result = accountService.getUser(user); 
		log.info("getUserDetails successfull for userName="+user.getName());
		ResponseEntity<User> response = new ResponseEntity<User>(result, HttpStatus.OK);
		return response;
	}

}


