package com.cts.fse.projectmanager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cts.fse.projectmanager.entity.Task;

public interface TaskRepository extends JpaRepository<Task, Long> {

	//@Query("SELECT u FROM Task u WHERE u.project.id = :projectId")
	List<Task> findByProjectId(Long projectId);
}
