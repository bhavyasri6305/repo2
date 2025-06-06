package com.jira.work.task_spbootapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.jira.work.task_spbootapp.exception.UserLoggedOutException;
import com.jira.work.task_spbootapp.exception.UserNotFoundException;
import com.jira.work.task_spbootapp.model.Task;
import com.jira.work.task_spbootapp.model.User;
import com.jira.work.task_spbootapp.service.TaskService;
import com.jira.work.task_spbootapp.service.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

	@Autowired
	private TaskService taskService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/task") //tasks,days,username 
	public ResponseEntity<String> saveTask(@RequestBody Task dto, 
			HttpServletRequest request){
		
		HttpSession session = request.getSession();
		
		String username = (String) session.getAttribute("uname");	
		String email = (String) session.getAttribute("email");	

		
		if(username == null && email == null) 
		            throw new UserLoggedOutException();
		
		String assignedTo = dto.getUsername();
		
		
		User user = userService.loadUserWithUsername(assignedTo);//manytoone 
		
		if(user== null) {
			throw new UserNotFoundException(dto.getUsername());
		}
		
		System.out.println("==================>"+user);
		dto.setUser(user);
		taskService.saveTask(dto);
		
		return ResponseEntity.ok("Task is saved successfully");
	}
	
	@GetMapping("/task/{username}") //retrieves the tasks of that user only not all tasks
	public ResponseEntity<?> getUserTasks(@PathVariable String username){
		return ResponseEntity.ok(taskService.getTasksByUser(username));	
	}
	
	@GetMapping("/task")   // retrieves the task of all users only not all tasks
	public ResponseEntity<?> getAllTasks(){
		return ResponseEntity.ok(taskService.getTasks());	
	}
	
	
	
}
