package com.cts.fse.projectmanager.bean;

import java.io.Serializable;
import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class TaskBean implements Serializable {

	private static final long serialVersionUID = 20190121;

	private Long id;
	
	private ProjectBean project;
	
	private Boolean isParentTask;
	
	private TaskBean parentTask;

	private String taskName;
	
	private LocalDate startDate;
	
	private LocalDate endDate;
	
	private Integer priority;
	
	private String status;
	
	private UserBean user;
		
	private boolean end;
}
