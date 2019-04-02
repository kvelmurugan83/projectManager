package com.cts.fse.projectmanager.controller;

import java.util.List;

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

import com.cts.fse.projectmanager.bean.UserBean;
import com.cts.fse.projectmanager.service.UserService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController()
@RequestMapping("projectmanager/api/user")
@CrossOrigin
public class UserController {

	@Autowired
	UserService service;

	@GetMapping()
	public List<UserBean> retrieveAll() {
		log.info("retrieveAll");
		return service.findAll();
	}

	@GetMapping("/findByEmpId/{empId}")
	public UserBean findByEmpId(@PathVariable Long empId) {
		log.info("retrieveAll");
		return service.findByEmpId(empId);
	}

	@PostMapping()
	public UserBean add(@RequestBody UserBean user) {
		log.info("Saving");
		return service.add(user);
	}

	@PutMapping()
	public UserBean update(@RequestBody UserBean user) {
		log.info("updating " + user);
		return service.update(user);
	}

	@GetMapping("/{id}")
	public UserBean retrieveById(@PathVariable Long id) {
		log.info("retrive By Id : " + id);
		try {
			UserBean user = service.findById(id);
			return user;
		} catch (EntityNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found", ex);
		}
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		log.info("Delete with ID " + id);
		service.delete(id);
	}
}
