package com.mssample.account.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
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
		log.debug("Trying login for user Name =" + user.getName());
		Optional<User> returnedUser = accountRepository.findByNameAndPassword(user.getName(), 
				user.getPassword()); 
		if(!returnedUser.isPresent())
			throw new UserNotFoundException("Invalid Login Credentails");
		return true;
	}

	public User getUser(User user){
		log.debug("Trying login for user Name =" + user.getName());
		Optional<User> returnedUser = accountRepository.findByName(user.getName()); 
		if(!returnedUser.isPresent())
			throw new UserNotFoundException("Invalid User Name");
		return returnedUser.get();
	}

	public User createUser(User user) {
		log.debug("Saving user =" + user);
		if(accountRepository.findByEmail(user.getEmail()).isPresent()){
			throw new EmailAlreadyRegisteredException("Email you mentioned is already registered");
		}
		if(accountRepository.findByName(user.getName()).isPresent()){
			throw new EmailAlreadyRegisteredException("User Name you mentioned is already registered");
		}
		user.setUserId(null);
		user.setPassword(user.getPassword());
		accountRepository.saveAndFlush(user);
		log.debug("Saved user");
		return user;
	}

	public boolean updateUser(User user) {
		log.debug("Updating user =" + user);
		Optional<User> returnedUser = accountRepository.findByName(user.getName()); 
		if(!returnedUser.isPresent())
			throw new UserNotFoundException("Invalid User Name");
		Optional<User> userEmail = accountRepository.findByEmail(user.getEmail());
		if(userEmail.isPresent() && !userEmail.get().getName().equalsIgnoreCase(user.getName())){
			throw new EmailAlreadyRegisteredException("Email you mentioned is already registered in the system");
		}
		Optional<User> userinDB = accountRepository.findByName(user.getName());
		BeanUtils.copyProperties(user, userinDB.get(), "userId");
		userinDB.get().setPassword(userinDB.get().getPassword());
		accountRepository.saveAndFlush(userinDB.get());
		log.debug("Updated user");
		return true;
	}

}
