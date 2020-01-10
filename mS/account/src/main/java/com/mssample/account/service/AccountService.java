package com.mssample.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mssample.account.exception.EmailAlreadyRegisteredException;
import com.mssample.account.exception.UserNotFoundException;
import com.mssample.account.model.User;
import com.mssample.account.repo.AccountRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AccountService {
	@Autowired 
	AccountRepository accountRepository;
	
	public boolean findUser(User user){
		log.debug("Trying login for user id =" + user.getUserId());
		User returnedUser = accountRepository.findByUserIdAndPassword(user.getUserId(), user.getPassword()); 
		if(returnedUser == null)
			throw new UserNotFoundException("Invalid Login Credentails");
		return true;
	}

	public boolean createUpdateUser(User user) {
		log.debug("Saving user =" + user);
		if(accountRepository.findByEmail(user.getEmail()) != null)
		{
			throw new EmailAlreadyRegisteredException("Email you mentioned is already registered");
		}
		accountRepository.saveAndFlush(user);
		log.debug("Saved user");
		return true;
	}

}
