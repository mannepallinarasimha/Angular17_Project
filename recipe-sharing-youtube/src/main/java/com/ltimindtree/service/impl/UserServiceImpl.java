package com.ltimindtree.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ltimindtree.config.JwtProvider;
import com.ltimindtree.entity.User;
import com.ltimindtree.repository.UserRepository;
import com.ltimindtree.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private JwtProvider jwtProvider;

	@Override
	public User addUser(User user) throws Exception  {
		User findByEmail = userRepository.findByEmail(user.getEmail());
		if(findByEmail != null) {
			throw new Exception("User already existed with email...."+user.getEmail());
//			() -> new RuntimeException("User Already exist with this email."+user.getEmail());
		}
		return userRepository.save(user);
	}

	@Override
	public User getUserById(Long id) {
		return userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id NOT present in DB"));
	}

	@Override
	public String deleteUserById(Long id) {
		userRepository.findById(id).orElseThrow(() -> new RuntimeException("User with id NOT present in DB"));
		return "User deleted successfully..."+id;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User findByJwt(String jwt) throws Exception{
		String emailFromJwtToken = jwtProvider.getEmailFromJwtToken(jwt);
		if(emailFromJwtToken == null) {
			throw new Exception("Provide valid jwt token....");
		}
		User findByEmail = userRepository.findByEmail(emailFromJwtToken);
		if(findByEmail == null) {
			throw new Exception("User NOT found from jwt token...."+findByEmail);
		}
		return findByEmail;
	}

}
