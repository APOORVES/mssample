package com.mssample.account;

import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.mssample.account.model.User;
import com.mssample.account.repo.AccountRepository;
import com.mssample.account.testdata.AllData;

@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(AccountApplication.class, args);
		testDataSetup(context);
	}

	private static void testDataSetup(ConfigurableApplicationContext context) {
			AccountRepository accountRepository = context.getBean(AccountRepository.class);
			List<User> users = AllData.createUsers();
			for(User user:users) {
				try{
					user.setUserId(0);
					accountRepository.saveAndFlush(user);
				}
				catch(Exception e){
					//Do Nothing
				}
			}
	}

}
