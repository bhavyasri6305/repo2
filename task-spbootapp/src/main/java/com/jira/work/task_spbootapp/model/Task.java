package com.jira.work.task_spbootapp.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Entity
public class Task {//users will have multiple tasks 
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String task;
	private int days;
	private boolean isCompleted;
	private String username;
	
	
	@ManyToOne
	@JsonIgnore       // Prevents recursive serialization
	                 //It won’t include the user field inside it.
					//This breaks the loop and avoids recursion. it will display complete task table
	private User user;

}
