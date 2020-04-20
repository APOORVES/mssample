package com.mssample.product.testdata;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.mssample.product.model.Deal;
import com.mssample.product.model.Product;
import com.mssample.product.model.Rating;
import com.mssample.product.model.Seller;
import com.mssample.product.model.User;

public class AllData {

	
	  public static List<Product> createProducts() { 
		List<Product> products = new ArrayList<Product>(); 
		products.add(createProduct1()); 
		products.add(createProduct2()); 
		products.add(createProduct3()); 
		return products; 
	  }
	 	
	
	@SuppressWarnings("deprecation")
	public static Product createProduct1() {
		Deal deal1 = new Deal("1", "Product1 Deal", null, new Timestamp(2019-1900, 1, 1, 0, 0, 0, 0), new Timestamp(2021-1900, 1, 1, 0, 0, 0, 0), 10);
		Seller seller1 = new Seller("1", "SellerOne", 5);
		Rating rating1 = new Rating("1", "user1", 4.5, "Very Good Product", "1");
		Rating rating2 = new Rating("2", "user2", 4.0, "Good Product", "1");
		Rating rating3 = new Rating("3", "user3", 3.0, "Ok Product", "1");
		List<Rating> ratings1 = new ArrayList<Rating>();
		ratings1.add(rating1);
		ratings1.add(rating2);
		ratings1.add(rating3);
		Product product1 = new Product("1", "Product1", "Product Short Description 1", "Product Description 1", "Category1", 10, 2, deal1, seller1, ratings1);
		deal1.setProduct(product1);
		return product1;
	}
	@SuppressWarnings("deprecation")
	public static Product createProduct2() {
		Deal deal2 = new Deal("2", "Product2 Deal", null, new Timestamp(2019-1900, 0, 1, 0, 0, 0, 0), new Timestamp(2021-1900, 0, 1, 0, 0, 0, 0), 20);
		Seller seller2 = new Seller("2", "SellerTwo", 10);
		Rating rating1 = new Rating("4", "user1", 4.5, "Very Good Product", "2");
		Rating rating2 = new Rating("5", "user2", 4.0, "Good Product", "2");
		Rating rating3 = new Rating("6", "user3", 3.0, "Ok Product", "2");
		List<Rating> ratings2 = new ArrayList<Rating>();
		ratings2.add(rating1);
		ratings2.add(rating2);
		ratings2.add(rating3);
		Product product2 = new Product("2", "Product2", "Product Short Description 2", "Product Description 2", "Category2", 10, 2, deal2, seller2, ratings2);
		deal2.setProduct(product2);
		return product2;
	}
	@SuppressWarnings("deprecation")
	public static Product createProduct3() {
		Deal deal3 = null;
		Seller seller3 = new Seller("1", "SellerThree", 15);
		Rating rating1 = new Rating("7", "user1", 4.5, "Very Good Product", "3");
		Rating rating2 = new Rating("8", "user2", 4.0, "Good Product", "3");
		Rating rating3 = new Rating("9", "user3", 3.0, "Ok Product", "3");
		List<Rating> ratings3 = new ArrayList<Rating>();
		ratings3.add(rating1);
		ratings3.add(rating2);
		ratings3.add(rating3);
		Product product3 = new Product("3", "Product3", "Product Short Description 3", "Product Description 3", "Category1", 10, 2, deal3, seller3, ratings3);
		return product3;
	}
	
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
			User user = new User(1l, "user1@domain.com", "UserOne", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser2() {
			User user = new User(2l, "user2@domain.com", "UserTwo", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser3() {
			User user = new User(3l, "user3@domain.com", "UserThree", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser4() {
			User user = new User(4l, "user4@domain.com", "UserFour", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
		public static User createUser5() {
			User user = new User(5l, "user5@domain.com", "UserFive", "pa$$w0rD", "pa$$w0rD");
			return user;
		}
	
	
}
