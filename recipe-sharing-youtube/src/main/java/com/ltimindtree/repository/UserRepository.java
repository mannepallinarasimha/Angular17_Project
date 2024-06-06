package com.ltimindtree.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ltimindtree.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);
	
}
