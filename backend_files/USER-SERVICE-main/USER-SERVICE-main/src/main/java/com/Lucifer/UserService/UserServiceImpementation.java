package com.Lucifer.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpementation implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User getUserProfile(String jwt) {
		String email = JwtProvider.getEmailFromJwtToken(jwt);
		return userRepository.findByEmail(email);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

}
