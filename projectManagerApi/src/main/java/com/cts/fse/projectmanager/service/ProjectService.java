package com.cts.fse.projectmanager.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.repository.ProjectRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProjectService {
	
	public ProjectService() {}
	
	public ProjectService(ProjectRepository repository) {
		this.repository = repository;
	}

	@Autowired
	private ProjectRepository repository;

	public Iterable<Project> findAll() {
		log.info("Find All Project");
		return repository.findAll();
	}

	public Project add(Project project) {
		log.info("Add Project  " + project);
		return repository.save(project);
	}

	public Project update(Project project) {
		log.info("Update Project " + project);
		return repository.save(project);
	}

	public void delete(Long id) {
		log.info("Delete with ID " + id);
		if (!repository.existsById(id)) {
			log.error("Not Found for Id " + id);
			throw new EntityNotFoundException("Not Found for Id " + id);
		}
		repository.deleteById(id);
	}

	public Project findById(Long projectId) {
		log.info("Find By Project Id" + projectId);
		Optional<Project> project = repository.findById(projectId);
		if (!project.isPresent()) {
			log.error("Project Not Found for Id " + projectId);
			throw new EntityNotFoundException("Project Not Found for Id " + projectId);
		}
		return project.get();
	}

	public void deleteAll() {
		log.info("Delete All Projects  ");
		this.repository.deleteAll();
	}

	public Project findByProjectName(String projectName) {
		log.info("find By Prjoect Name " + projectName);
		List<Project> project = repository.findByProject(projectName);
		if (project != null && project.size() > 0) {
			return project.get(0);
		}
		return null;
	}
}
