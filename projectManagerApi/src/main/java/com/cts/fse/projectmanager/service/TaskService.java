package com.cts.fse.projectmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.bean.TaskBean;
import com.cts.fse.projectmanager.entity.Task;
import com.cts.fse.projectmanager.repository.TaskRepository;
import com.cts.fse.projectmanager.transformer.ProjectTransformer;
import com.cts.fse.projectmanager.transformer.TaskTransformer;
import com.cts.fse.projectmanager.transformer.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TaskService {

	public TaskService() {
	}

	public TaskService(TaskRepository repository, UserService userService, ProjectService projectService) {
		this.repository = repository;
		this.projectService = projectService;
		this.userService = userService;
	}

	@Autowired
	private TaskRepository repository;
	@Autowired
	private UserService userService;
	@Autowired
	private ProjectService projectService;

	public List<TaskBean> findAll() {
		Iterable<Task> tasks = repository.findAll();
		return StreamSupport.stream(tasks.spliterator(), false).map(TaskTransformer::toBean)
				.collect(Collectors.toList());
	}

	public TaskBean add(TaskBean t) {
		Task task = TaskTransformer.toEntity(t);
		log.info("Add Task  " + task);
		this.updateReference(task);
		return TaskTransformer.toBean(repository.save(task));
	}

	private void updateReference(Task task) {
		if (task.getParentTask() != null && task.getParentTask().getId() != null) {
			task.setParentTask(TaskTransformer.toEntity(this.findById(task.getParentTask().getId())));
		} else {
			task.setParentTask(null);
		}
		if (task.getUser() != null)
			task.setUser(UserTransformer.toEntity(this.userService.findById(task.getUser().getId())));
		if (task.getProject() != null)
			task.setProject(ProjectTransformer.toEntity(this.projectService.findById(task.getProject().getId())));
	}

	public TaskBean update(TaskBean t) {
		Task task = TaskTransformer.toEntity(t);
		log.info("Update Task  " + task);
		this.updateReference(task);
		return TaskTransformer.toBean(repository.save(task));
	}

	public void delete(Long id) {
		log.info("Delete with ID " + id);
		if (!repository.existsById(id)) {
			log.error("Not Found for Id " + id);
			throw new EntityNotFoundException("Not Found for Id " + id);
		}
		repository.deleteById(id);
	}

	public List<TaskBean> findByProjectId(Long projectId) {
		log.info("Find By Project Id " + projectId);
		List<Task> tasks = repository.findByProjectId(projectId);
		return tasks.stream().map(TaskTransformer::toBean).collect(Collectors.toList());
	}

	public TaskBean findById(Long id) {
		log.info("Find By Task Id " + id);
		Optional<Task> task = repository.findById(id);
		if (!task.isPresent()) {
			log.error("Task Not Found for Id " + id);
			throw new EntityNotFoundException("Task Not Found for Id " + id);
		}
		return TaskTransformer.toBean(task.get());
	}

	public void deleteAll() {
		log.info("Delete All Task  ");
		this.repository.deleteAll();
	}
}
