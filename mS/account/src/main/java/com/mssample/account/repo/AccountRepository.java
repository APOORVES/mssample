package com.mssample.account.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mssample.account.model.User;

@Repository
public interface AccountRepository extends JpaRepository<User, Long> {
	Optional<User> findByEmail(String email);
	Optional<User> findByUserId(long userId);
	Optional<User> findByName(String userName);
	Optional<User> findByNameAndPassword(String name, String password);
}
