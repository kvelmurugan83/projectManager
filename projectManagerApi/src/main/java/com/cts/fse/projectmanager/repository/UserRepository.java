package com.cts.fse.projectmanager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.cts.fse.projectmanager.entity.User;

public interface UserRepository extends CrudRepository<User, Long> {
	
	public List<User> findByEmpId(Long empId);
}
