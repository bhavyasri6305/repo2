package com.jira.work.task_spbootapp.exception;

public class UserNotFoundException extends RuntimeException {

	public UserNotFoundException(String username) {
		super(username);
	}

}
