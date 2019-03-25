package com.cts.fse.projectmanager.repository;

import org.springframework.data.repository.CrudRepository;

import com.cts.fse.projectmanager.entity.Task;

public interface TaskRepository extends CrudRepository<Task, Long> {

}
