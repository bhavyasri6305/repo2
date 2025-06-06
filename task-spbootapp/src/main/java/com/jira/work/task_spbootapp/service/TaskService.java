package com.jira.work.task_spbootapp.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jira.work.task_spbootapp.model.Task;
import com.jira.work.task_spbootapp.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task saveTask(Task dto) {
		return taskRepository.save(dto);
	}

	
	public List<Task> getTasks() {	
		return taskRepository.findAll();
	}
	
	public Task getTasksById(long id) {
		Optional <Task>  optional = taskRepository.findById(id);
		if(optional.isPresent())
			return optional.get();
		return null;	
	}


	public List<Task> getTasksByUser(String username) {
		List<Task> userTasks = this.getTasks().stream()
				                   .filter(task -> task.getUsername().equals(username))
				                   .collect(Collectors.toList());
		return userTasks;
	}
	
	
	
	
	
}
