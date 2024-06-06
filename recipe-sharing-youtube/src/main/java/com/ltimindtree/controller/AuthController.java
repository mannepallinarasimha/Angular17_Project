package com.ltimindtree.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ltimindtree.config.JwtProvider;
import com.ltimindtree.entity.User;
import com.ltimindtree.repository.UserRepository;
import com.ltimindtree.request.LoginRequest;
import com.ltimindtree.response.AuthResponse;
import com.ltimindtree.service.impl.CustomeUserDetailsService;

@RestController
@RequestMapping(path="auth")
public class AuthController {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CustomeUserDetailsService customeUserDetailsService;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@PostMapping(path="signUp")
	public AuthResponse createUser(@RequestBody User user)throws Exception{
		//1.get the details from user 
		String email = user.getEmail();
		String password = user.getPassword();
		String fullName = user.getFullName();
		
		//2. check user exit in DB or NOT 
		User findByEmail = userRepository.findByEmail(email);
		if(findByEmail != null) {
			throw new Exception("Email is alredy used with Another Account: "+findByEmail.getEmail());
		}
		//3. if user NOT exist in DB set the details to user and save to DB
		User createdUser = new User();
		createdUser.setEmail(email);
		createdUser.setPassword(passwordEncoder.encode(password));
		createdUser.setFullName(fullName);
		User savedUser = userRepository.save(createdUser);
		
		//4. create Authentication for 
		Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);
		//5. set this Authentication to SecurityContextHolder
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		//6. create one Jwt Token 
		String token  = jwtProvider.generateToken(authentication);
		
		//7. Need to create one AuthResponse for return purpose
		
		return new AuthResponse(token, "Signup Successfull...");
	}
	@PostMapping(path="signIn")
	public AuthResponse signInHandler(@RequestBody LoginRequest loginRequest) throws Exception {
		String email = loginRequest.getEmail();
		String password = loginRequest.getPassword();
		Authentication authentication = authenticate(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String token  = jwtProvider.generateToken(authentication);
		return new AuthResponse(token, "Signup Successfull...");
	}


	private Authentication authenticate(String email, String password) throws Exception {
//		User findByEmail = userRepository.findByEmail(email);
		UserDetails loadUserByUsername = customeUserDetailsService.loadUserByUsername(email);
		if(loadUserByUsername == null) {
			throw new BadCredentialsException("User with email already exist in DB: "+email);
		}
		if(!passwordEncoder.matches(password, loadUserByUsername.getPassword())) {
			throw new BadCredentialsException("Invalid password");
		}
		return new UsernamePasswordAuthenticationToken(loadUserByUsername, null, loadUserByUsername.getAuthorities());
	}

}
