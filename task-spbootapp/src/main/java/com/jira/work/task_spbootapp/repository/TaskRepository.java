package com.jira.work.task_spbootapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jira.work.task_spbootapp.model.Task;

public interface TaskRepository  extends JpaRepository<Task, Long>{
		

}
//Provides CRUD operations for Task entity.