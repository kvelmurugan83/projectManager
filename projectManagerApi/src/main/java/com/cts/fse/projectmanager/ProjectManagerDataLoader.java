package com.cts.fse.projectmanager;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Service;

import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.entity.Task;
import com.cts.fse.projectmanager.entity.User;
import com.cts.fse.projectmanager.service.ProjectService;
import com.cts.fse.projectmanager.service.TaskService;
import com.cts.fse.projectmanager.service.UserService;

@Service
public class ProjectManagerDataLoader implements ApplicationRunner {

	@Autowired
	private ProjectService projectService;
	@Autowired
	private UserService userService;
	@Autowired
	private TaskService taskService;

	@Override
	public void run(ApplicationArguments args) throws Exception {
		this.taskService.deleteAll();
		this.projectService.deleteAll();
		this.userService.deleteAll();
		User vel = this.userService
				.add(User.builder().firstName("Velmurugan").lastName("Kandasamy").empId(new Long(10001)).build());
		User kumar = this.userService
				.add(User.builder().firstName("Kumar").lastName("Moorthy").empId(new Long(10002)).build());
		User karthik = this.userService
				.add(User.builder().firstName("Karthik").lastName("Kannan").empId(new Long(10003)).build());
		User krishna = this.userService
				.add(User.builder().firstName("Krishnaraj").lastName("Jeganathan").empId(new Long(10004)).build());

		Project dataManagement = this.projectService.add(Project.builder().project("Data management").manager(karthik)
				.priority(15).startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(5)).build());

		Project appManagement = this.projectService.add(Project.builder().project("Application management")
				.manager(kumar).priority(15).startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(15)).build());

		Task createData = this.taskService.add(Task.builder().project(dataManagement).isParentTask(true).taskName("Create Data").priority(10)
				.status("NEW").startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(10)).user(krishna).build());
		
		Task updateData = this.taskService.add(Task.builder().project(dataManagement).isParentTask(false).parentTask(createData).taskName("Create Data").priority(10)
				.status("NEW").startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(10)).user(vel).build());

		Task createApp = this.taskService.add(Task.builder().project(appManagement).isParentTask(true).taskName("Create App").priority(10)
				.status("NEW").startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(10)).user(vel).build());
		
		Task updateApp = this.taskService.add(Task.builder().project(appManagement).isParentTask(false).parentTask(createApp).taskName("Update App").priority(10)
				.status("NEW").startDate(LocalDate.now()).endDate(LocalDate.now().plusDays(10)).user(krishna).build());
	}

}
