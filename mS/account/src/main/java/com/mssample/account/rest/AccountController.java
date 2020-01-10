package com.mssample.account.rest;

import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
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
	public ResponseEntity<Boolean> login(@RequestBody User user){
		log.info("login invoked for userId="+user.getUserId());
		Boolean result = accountService.findUser(user); 
		log.info("login successfull for userId="+user.getUserId());
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		return response;
	}

	@PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity signup(@Valid @RequestBody User user, Errors errors){
		log.info("signup invoked for user = "+user.getUserId());
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
			result = accountService.createUpdateUser(user);
		}
		log.info("signup created account "+result);
		ResponseEntity<Boolean> responseHttp = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		log.info("signup returning response="+responseHttp);
		return responseHttp;
	}

	@PostMapping(value = "/{userid}/update", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Boolean> accountUpdate(@RequestBody User user){
		log.info("accountUpdate for userid="+user.getUserId());
		boolean result = accountService.createUpdateUser(user); 
		log.info("sucessfully udpated the account for userid="+user.getUserId());
		ResponseEntity<Boolean> response = new ResponseEntity<Boolean>(result, HttpStatus.OK);
		return response;
	}

}


