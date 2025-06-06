package com.jira.work.task_spbootapp.dto;

import lombok.Data;

@Data
public class LoginRequestDto {
	
	//A helper class to handle login input. Can accept either email or username.
	
	String username;
	String email;
	String password;
	
}
