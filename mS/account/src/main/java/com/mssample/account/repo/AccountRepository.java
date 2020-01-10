package com.mssample.account.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mssample.account.model.User;

@Repository
public interface AccountRepository extends JpaRepository<User, String> {
	User findByUserIdAndPassword(long userId, String password);
	User findByEmail(String email);
}
