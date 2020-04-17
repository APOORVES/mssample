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
		users.add(createUser6()); 
		return users; 
	  }
		public static User createUser1() {
			User user = new User(null, "user1@domain.com", "UserOne", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser2() {
			User user = new User(null, "user2@domain.com", "UserTwo", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser3() {
			User user = new User(null, "user3@domain.com", "UserThree", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser4() {
			User user = new User(null, "user4@domain.com", "UserFour", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser5() {
			User user = new User(null, "user5@domain.com", "UserFive", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser6() {
			User user = new User(null, "username@domain.com", "username", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
	
	
}
