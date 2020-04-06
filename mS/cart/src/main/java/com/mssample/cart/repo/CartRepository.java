package com.mssample.cart.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mssample.cart.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	Optional<Cart> findByUserId(long userId);
}
