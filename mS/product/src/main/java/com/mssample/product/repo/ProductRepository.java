package com.mssample.product.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mssample.product.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {
	List<Product> findByDisplayNameLike(String displayName);
	List<Product> findByDisplayName(String displayName);
	List<Product> findByDisplayNameAndCategory(String displayName, String productCategory);

}
