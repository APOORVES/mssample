package com.mssample.account.testdata;

import java.util.ArrayList;
import java.util.List;

import com.mssample.account.model.User;

public class AllData {

	
		
	  public static List<User> createUsers() { 
		List<User> users = new ArrayList<User>(); 
		users.add(createUser1()); 
		users.add(createUser2()); 
		users.add(createUser3()); 
		users.add(createUser4()); 
		users.add(createUser5()); 
		return users; 
	  }
		public static User createUser1() {
			User user = new User(1, "user1@domain.com", "UserOne", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser2() {
			User user = new User(2, "user2@domain.com", "UserTwo", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser3() {
			User user = new User(3, "user3@domain.com", "UserThree", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser4() {
			User user = new User(4, "user4@domain.com", "UserFour", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser5() {
			User user = new User(5, "user5@domain.com", "UserFive", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
	
	
}
