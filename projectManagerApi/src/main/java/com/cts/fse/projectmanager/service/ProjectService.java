package com.cts.fse.projectmanager.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.bean.ProjectBean;
import com.cts.fse.projectmanager.bean.TaskBean;
import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.repository.ProjectRepository;
import com.cts.fse.projectmanager.transformer.ProjectTransformer;
import com.cts.fse.projectmanager.transformer.UserTransformer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectService {

	public ProjectService() {
	}

	public ProjectService(ProjectRepository repository, UserService userService, TaskService taskService) {
		this.repository = repository;
		this.userService = userService;
		this.taskService = taskService;
	}

	@Autowired
	private ProjectRepository repository;

	@Autowired
	private UserService userService;

	@Autowired
	private TaskService taskService;

	public List<ProjectBean> findAll() {
		log.info("Find All Project");
		Iterable<Project> result = repository.findAll();
		result.forEach(p -> {
			List<TaskBean> tasks = taskService.findByProjectId(p.getId());
			int totalTasks = 0;
			if (tasks != null) {
				totalTasks = tasks.size();
			}
			p.setNoOfTasks(totalTasks);
		});
		return StreamSupport.stream(result.spliterator(), false).map(ProjectTransformer::toBean)
				.collect(Collectors.toList());
	}

	public ProjectBean add(ProjectBean p) {
		log.info("Add Project  " + p);
		Project project = ProjectTransformer.toEntity(p);
		if (project.getManager() != null)
			project.setManager(UserTransformer.toEntity(this.userService.findById(project.getManager().getId())));
		return ProjectTransformer.toBean(repository.save(project));
	}

	public ProjectBean update(ProjectBean p) {
		log.info("Update Project " + p);
		Project project = ProjectTransformer.toEntity(p);
		if (project.getManager() != null)
			project.setManager(UserTransformer.toEntity(this.userService.findById(project.getManager().getId())));
		return ProjectTransformer.toBean(repository.save(project));
	}

	public void delete(Long id) {
		log.info("Delete with ID " + id);
		if (!repository.existsById(id)) {
			log.error("Not Found for Id " + id);
			throw new EntityNotFoundException("Not Found for Id " + id);
		}
		repository.deleteById(id);
	}

	public ProjectBean findById(Long projectId) {
		log.info("Find By Project Id" + projectId);
		Optional<Project> project = repository.findById(projectId);
		if (!project.isPresent()) {
			log.error("Project Not Found for Id " + projectId);
			throw new EntityNotFoundException("Project Not Found for Id " + projectId);
		}
		return ProjectTransformer.toBean(project.get());
	}

	public void deleteAll() {
		log.info("Delete All Projects  ");
		this.repository.deleteAll();
	}

	public ProjectBean findByProjectName(String projectName) {
		log.info("find By Prjoect Name " + projectName);
		List<Project> project = repository.findByProject(projectName);
		if (project != null && project.size() > 0) {
			return ProjectTransformer.toBean(project.get(0));
		}
		return null;
	}
}
