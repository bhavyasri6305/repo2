package com.jira.work.task_spbootapp.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name="Users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue auto-generates the ID.
	private long id;
	private String username;
	private String password;
	private String email;
	
	
	@OneToMany(mappedBy = "user")  //One user can have many tasks.
	private List<Task> tasks; 
}
