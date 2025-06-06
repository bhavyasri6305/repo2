package com.jira.work.task_spbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jira.work.task_spbootapp.dto.LoginRequestDto;
import com.jira.work.task_spbootapp.model.User;
import com.jira.work.task_spbootapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User dto) {
		return userService.saveUser(dto);
	}
	
	@GetMapping("/user/{userId}")
	public User getUserById(@PathVariable long userId) {
		return userService.getUserById(userId);
	}
	
	@GetMapping("/user")
	public List<User> findAll() {
		return userService.findAll();
	}
	
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDto dto,HttpServletRequest request) {
		boolean isAuthenticated = false;	
		HttpSession session = request.getSession();

		if(dto.getEmail()!= null && !dto.getEmail().isEmpty()) {
			session.setAttribute("email", dto.getEmail());

			
			isAuthenticated = userService.loginWithEmail(dto.getEmail(), dto.getPassword());
		}
		else if(dto.getUsername()!= null && !dto.getUsername().isEmpty()) {
			
			session.setAttribute("uname", dto.getUsername());

			isAuthenticated = userService.loginWithUsername(dto.getUsername(), dto.getPassword());
			
		}else {
			//If neither is provided, return 400 Bad Request.
			return ResponseEntity.badRequest().body("Email or Username should be provided");
		}
		
		
		//sets session info if valid, and returns an appropriate HTTP response.
		//A session is created (or reused).
		//The logged-in user's username is stored in session with key "uname" — for 
		//identifying the user in future requests (like saving tasks).

		if(isAuthenticated) {
			return ResponseEntity.ok("Login successfull");
		}else {
			return ResponseEntity.status(401).body("Invalid Credentials");
		}
	}
}
//	What is ResponseEntity in Spring Boot?
//	ResponseEntity<T> is a generic class used in Spring Boot to represent the entire HTTP response — including:
//	Status code (e.g. 200 OK, 401 UNAUTHORIZED)
//	Headers
//	Body (data of type T)
	
	

