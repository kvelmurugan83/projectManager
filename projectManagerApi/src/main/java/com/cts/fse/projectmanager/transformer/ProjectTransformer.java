package com.cts.fse.projectmanager.transformer;

import com.cts.fse.projectmanager.bean.ProjectBean;
import com.cts.fse.projectmanager.entity.Project;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProjectTransformer {

	public static Project toEntity(ProjectBean b) {
		log.info("toEntity  " + b);
		Project p = new Project();
		if (b.getEndDate() != null) {
			p.setEndDate(b.getEndDate());
		}
		p.setId(b.getId());
		if (b.getManager() != null) {
			p.setManager(UserTransformer.toEntity(b.getManager()));
		}
		p.setNoOfTasks(b.getNoOfTasks());
		p.setPriority(b.getPriority());
		p.setProject(b.getProject());
		if (b.getStartDate() != null)
			p.setStartDate(b.getStartDate());
		p.setSuspend(b.getSuspend());
		return p;
	}

	public static ProjectBean toBean(Project b) {
		log.info("toEntity  " + b);
		ProjectBean p = new ProjectBean();
		if (b.getEndDate() != null)
			p.setEndDate(b.getEndDate());
		p.setId(b.getId());
		if (b.getManager() != null) {
			p.setManager(UserTransformer.toBean(b.getManager()));
		}
		p.setNoOfTasks(b.getNoOfTasks());
		p.setPriority(b.getPriority());
		p.setProject(b.getProject());
		if (b.getStartDate() != null)
			p.setStartDate(b.getStartDate());
		p.setSuspend(b.getSuspend());
		return p;
	}
}
