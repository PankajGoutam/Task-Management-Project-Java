package com.Lucifer.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
	@Autowired
	private UserService userService;
	
	@CrossOrigin
	@GetMapping("/api/users/profile")
	public ResponseEntity<User> getUserProfile(@RequestHeader("Authorization")String jwt){
		User user = userService.getUserProfile(jwt);
		System.out.println("sign in userDetails :- "+user);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@CrossOrigin
	@GetMapping("/api/users")
	public ResponseEntity<List<User>> getAllUsers(

			@RequestHeader("Authorization") String jwt)  {

		List<User> users = userService.getAllUsers();


		return new ResponseEntity<>(users, HttpStatus.ACCEPTED);
	}

	
}
