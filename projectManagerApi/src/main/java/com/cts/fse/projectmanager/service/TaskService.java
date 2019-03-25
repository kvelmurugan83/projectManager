package com.cts.fse.projectmanager.service;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.entity.Task;
import com.cts.fse.projectmanager.repository.TaskRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService {
	
	public TaskService() {}
	
	public TaskService(TaskRepository repository) {
		this.repository = repository;
	}

	@Autowired
	private TaskRepository repository;

	public Iterable<Task> findAll() {
		return repository.findAll();
	}

	public Task add(Task task) {
		log.info("Add Task  " + task);
		return repository.save(task);
	}

	public Task update(Task task) {
		log.info("Update Task  " + task);
		return repository.save(task);
	}

	public void delete(Long id) {
		log.info("Delete with ID " + id);
		if (!repository.existsById(id)) {
			log.error("Not Found for Id " + id);
			throw new EntityNotFoundException("Not Found for Id " + id);
		}
		repository.deleteById(id);
	}

	public Task findById(Long id) {
		log.info("Find By Task Id " + id);
		Optional<Task> task = repository.findById(id);
		if (!task.isPresent()) {
			log.error("Task Not Found for Id " + id);
			throw new EntityNotFoundException("Task Not Found for Id " + id);
		}
		return task.get();
	}

	public void deleteAll() {
		log.info("Delete All Task  ");
		this.repository.deleteAll();
	}
}
