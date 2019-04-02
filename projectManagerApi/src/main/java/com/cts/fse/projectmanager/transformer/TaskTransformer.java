package com.cts.fse.projectmanager.transformer;

import com.cts.fse.projectmanager.bean.TaskBean;
import com.cts.fse.projectmanager.entity.Task;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TaskTransformer {

	public static Task toEntity(TaskBean b) {
		log.info("toEntity  " + b);
		Task t = new Task();
		if (b.getId() != null) {
			t.setId(b.getId());
		}
		if (b.getEndDate() != null)
			t.setEndDate(b.getEndDate());
		t.setIsParentTask(b.getIsParentTask());
		if (b.getParentTask() != null) {
			t.setParentTask(toEntity(b.getParentTask()));
		}
		t.setPriority(b.getPriority());
		if (b.getProject() != null) {
			t.setProject(ProjectTransformer.toEntity(b.getProject()));
		}
		if (b.getStartDate() != null)
			t.setStartDate(b.getStartDate());
		t.setStatus(b.getStatus());
		t.setTaskName(b.getTaskName());
		if (b.getUser() != null) {
			t.setUser(UserTransformer.toEntity(b.getUser()));
		}
		t.setEnd(b.isEnd());
		return t;
	}

	public static TaskBean toBean(Task b) {
		log.info("toBean  " + b);
		TaskBean t = new TaskBean();
		t.setId(b.getId());
		if (b.getEndDate() != null)
			t.setEndDate(b.getEndDate());
		t.setIsParentTask(b.getIsParentTask());
		if (b.getParentTask() != null) {
			t.setParentTask(toBean(b.getParentTask()));
		}
		t.setPriority(b.getPriority());
		if (b.getProject() != null) {
			t.setProject(ProjectTransformer.toBean(b.getProject()));
		}
		if (b.getStartDate() != null)
			t.setStartDate(b.getStartDate());
		t.setStatus(b.getStatus());
		t.setTaskName(b.getTaskName());
		if (b.getUser() != null) {
			t.setUser(UserTransformer.toBean(b.getUser()));
		}
		t.setEnd(b.isEnd());
		return t;
	}
}
