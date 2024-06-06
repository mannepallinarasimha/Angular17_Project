package com.ltimindtree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.entity.User;
import com.ltimindtree.service.UserService;

@RestController
@RequestMapping(path="api/v1/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
//	@Autowired
//	private UserService userService;
//	
//	@PostMapping(path="create")
//	public User createUser(@RequestBody User user) throws Exception {
//		return userService.addUser(user);
//	}
//	
//	@GetMapping(path="get/{id}")
//	public User getUserById(@PathVariable("id") Long id) {
//		return userService.getUserById(id);
//	}
//	
//	@DeleteMapping(path="delete/{id}")
//	public String deleteUserById(@PathVariable("id") Long id) {
//		return userService.deleteUserById(id);
//	}
//	
//	@GetMapping(path="getAllUsers")
//	public List<User> getAllUsers() {
//		return userService.getAllUsers();
//	}
	
	@GetMapping(path="profile")
	public User getUserByJwtToken(@RequestHeader("Authorization") String jwt) throws Exception {
		return userService.findByJwt(jwt);
	}
	

}
