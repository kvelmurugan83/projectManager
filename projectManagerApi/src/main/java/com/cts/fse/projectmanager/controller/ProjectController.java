package com.cts.fse.projectmanager.controller;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.service.ProjectService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("projectmanager/api/project")
@CrossOrigin
public class ProjectController {

	@Autowired
	ProjectService service;

	@GetMapping()
	public Iterable<Project> retrieveAll() {
		log.info("retrieveAll");
		return service.findAll();
	}

	@PostMapping()
	public Project add(@RequestBody Project project) {
		log.info("Saving");
		return service.add(project);
	}

	@PutMapping()
	public Project update(@RequestBody Project project) {
		log.info("updating " + project);
		return service.update(project);
	}

	@GetMapping("/{id}")
	public Project retrieveById(@PathVariable Long id) {
		log.info("retrive By Id : " + id);
		try {
			Project project = service.findById(id);
			return project;
		} catch (EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Project Not Found", ex);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.info("Delete with ID " + id);
		service.delete(id);
	}
}
