package com.mssample.cart.testdata;

import java.util.ArrayList;
import java.util.List;

import com.mssample.account.model.User;
import com.mssample.cart.model.Cart;
import com.mssample.cart.model.CartItem;
import com.mssample.product.model.Product;
import com.mssample.product.model.Seller;

public class CartAllData {

	
	  public static List<Cart> createCarts() { 
		List<Cart> carts = new ArrayList<Cart>(); 
		carts.add(createCart1()); 
		carts.add(createCart2()); 
		carts.add(createCart3()); 
		return carts; 
	  }

	  
	  private static Cart createCart1() {
		ArrayList<CartItem> cartSelections = new ArrayList<>();
		Cart cart = new Cart("UserOne", cartSelections);
		CartItem cartItem = new CartItem("Product1", 1, 8, cart);
		cartSelections.add(cartItem);
		return cart;
	}
	  private static Cart createCart2() {
		ArrayList<CartItem> cartSelections = new ArrayList<>();
		Cart cart = new Cart("UserOne", cartSelections);
		CartItem cartItem = new CartItem("Product2", 4, 8, cart);
		cartSelections.add(cartItem);
		return cart;
	}
	  private static Cart createCart3() {
		ArrayList<CartItem> cartSelections = new ArrayList<>();
		Cart cart = new Cart("UserOne", cartSelections);
		CartItem cartItem = new CartItem("Product5", 5, 8, cart);
		cartSelections.add(cartItem);
		return cart;
	}


	public static List<Product> createProducts() { 
		List<Product> products = new ArrayList<Product>(); 
		products.add(createProduct1()); 
		products.add(createProduct2()); 
		products.add(createProduct3()); 
		return products; 
	  }
	 	
	
	public static Product createProduct1() {
		Seller seller1 = new Seller("1", "SellerOne", 5);
		Product product1 = new Product("1", "Product1", "Product Short Description 1", "Product Description 1", "Category1", 10, 2, 8, seller1);
		return product1;
	}
	public static Product createProduct2() {
		Seller seller2 = new Seller("2", "SellerTwo", 10);
		Product product2 = new Product("2", "Product2", "Product Short Description 2", "Product Description 2", "Category2", 20, 3, 15, seller2);
		return product2;
	}
	public static Product createProduct3() {
		Seller seller3 = new Seller("1", "SellerThree", 15);
		Product product3 = new Product("3", "Product3", "Product Short Description 3", "Product Description 3", "Category1", 10, 2, 8, seller3);
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
