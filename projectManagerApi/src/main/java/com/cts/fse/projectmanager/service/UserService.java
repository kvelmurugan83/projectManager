package com.cts.fse.projectmanager.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.entity.User;
import com.cts.fse.projectmanager.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class UserService {
	
	public UserService() {}
	
	public UserService(UserRepository repository) {
		this.repository = repository;
	}

	@Autowired
	private UserRepository repository;

	public Iterable<User> findAll() {
		return repository.findAll();
	}

	public User add(User user) {
		log.info("Add user " + user);
		return repository.save(user);
	}

	public User update(User user) {
		log.info("Update user " + user);
		return repository.save(user);
	}

	public void delete(Long id) {
		log.info("Delete with ID " + id);
		if (!repository.existsById(id)) {
			log.error("Not Found for Id " + id);
			throw new EntityNotFoundException("Not Found for Id " + id);
		}
		repository.deleteById(id);
	}

	public User findById(Long id) {
		log.info("find By ID " + id);
		Optional<User> user = repository.findById(id);
		if (!user.isPresent()) {
			log.error("User Not Found for Id " + id);
			throw new EntityNotFoundException("User Not Found for Id " + id);
		}
		return user.get();
	}

	public User findByEmpId(Long empId) {
		log.info("find By emp id " + empId);
		List<User> user = repository.findByEmpId(empId);
		if (user != null && user.size() > 0) {
			return user.get(0);
		}
		return null;
	}

	public void deleteAll() {
		log.info("Delete All " );
		this.repository.deleteAll();
	}
}
