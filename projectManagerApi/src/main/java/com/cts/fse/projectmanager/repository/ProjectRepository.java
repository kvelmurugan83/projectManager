package com.cts.fse.projectmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.fse.projectmanager.entity.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {
	
	public List<Project> findByProject(String project);
}
