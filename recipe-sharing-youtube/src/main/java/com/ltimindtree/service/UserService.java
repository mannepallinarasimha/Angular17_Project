package com.ltimindtree.service;

import java.util.List;

import com.ltimindtree.entity.User;

public interface UserService {

	User addUser(User user) throws Exception;

	User getUserById(Long id);

	String deleteUserById(Long id);

	List<User> getAllUsers();
	public User findByJwt(String jwt)throws Exception;
}
