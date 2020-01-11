package com.mssample.account.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
	@Autowired
	PasswordEncoder passwordEncoder;
	
	public boolean findUser(User user){
		log.debug("Trying login for user Name =" + user.getName());
		User returnedUser = accountRepository.findByNameAndPassword(user.getName(), user.getPassword()); 
		if(returnedUser == null)
			throw new UserNotFoundException("Invalid Login Credentails");
		return true;
	}

	public User getUser(User user){
		log.debug("Trying login for user Name =" + user.getName());
		User returnedUser = accountRepository.findByName(user.getName()); 
		if(returnedUser == null)
			throw new UserNotFoundException("Invalid User Name");
		return returnedUser;
	}

	public boolean createUser(User user) {
		log.debug("Saving user =" + user);
		if(accountRepository.findByEmail(user.getEmail()) != null)
		{
			throw new EmailAlreadyRegisteredException("Email you mentioned is already registered");
		}
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		accountRepository.saveAndFlush(user);
		log.debug("Saved user");
		return true;
	}

	public boolean updateUser(User user) {
		log.debug("Updating user =" + user);
		User returnedUser = accountRepository.findByName(user.getName()); 
		if(returnedUser == null)
			throw new UserNotFoundException("Invalid User Name");
		User userEmail = accountRepository.findByEmail(user.getEmail());
		if(userEmail != null && !userEmail.getName().equalsIgnoreCase(user.getName())){
			throw new EmailAlreadyRegisteredException("Email you mentioned is already registered in the system");
		}
		User userinDB = accountRepository.findByName(user.getName());
		BeanUtils.copyProperties(user, userinDB);
		userinDB.setPassword(passwordEncoder.encode(userinDB.getPassword()));
		accountRepository.saveAndFlush(userinDB);
		log.debug("Updated user");
		return true;
	}

}
