package com.mssample.cart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mssample.cart.model.Cart;

/**
 * Cart Repository which reads and writes cart records into databse
 * @author Apoorve
 *
 */
public interface CartRepository extends JpaRepository<Cart, Long>{
	/**
	 * @param userName
	 * @return
	 */
	Optional<Cart> findByUserName(String userName);
}
