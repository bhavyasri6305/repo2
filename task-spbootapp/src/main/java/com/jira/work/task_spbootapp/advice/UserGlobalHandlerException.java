package com.jira.work.task_spbootapp.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jira.work.task_spbootapp.exception.UserLoggedOutException;
import com.jira.work.task_spbootapp.exception.UserNotFoundException;

@RestControllerAdvice
public class UserGlobalHandlerException {
	@ExceptionHandler(UserLoggedOutException.class)
	public ResponseEntity<String> handleUserLogoutException(UserLoggedOutException e) {
		
		return new ResponseEntity<>("User is not logged in please login now",HttpStatus.UNAUTHORIZED);
		

	}
	
	
	
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
		
		return new ResponseEntity<>("User with"+e.getMessage()+" not found ",HttpStatus.UNAUTHORIZED);
	}

}


//@RestControllerAdvice: Catches exceptions 
//globally across all controllers.
//
//@ExceptionHandler: Handles UserLoggedOutException and returns a 
//401 Unauthorized response.