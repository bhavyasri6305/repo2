package com.jira.work.task_spbootapp.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.work.task_spbootapp.exception.UserLoggedOutException;
import com.jira.work.task_spbootapp.model.User;
import com.jira.work.task_spbootapp.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public User saveUser(User dto) {
		return userRepository.save(dto);
	}
	public boolean loginWithEmail(String email,String password) {
		return userRepository.existsByEmailAndPassword(email, password);
	}
	public boolean loginWithUsername(String username, String password) {
		return userRepository.existsByUsernameAndPassword(username, password);
	}
	
	public User loadUserWithUsername(String username) {
		
		return userRepository.findUserByUsername(username);
	}
	public User getUserById(long userId) {
		boolean isPresent = userRepository.existsById(userId);
		if(!isPresent)
			throw new UserLoggedOutException();
		
		Optional<User> optional = userRepository.findById(userId);
		if(optional.isPresent())
			return optional.get();
		
		return null;
	}
	public List<User> findAll() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}
}
