package com.cts.fse.projectmanager.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Task implements Serializable {

	private static final long serialVersionUID = 20190121;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TASK_ID")
	private Long taskId;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PROJECT_ID")
	private Project project;
	
	@Column(name = "IS_PARENT")
	private Boolean isParentTask;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "PARENT_ID")
	private Task parentTask;
	
	@Column(name = "TASK")
	private String taskName;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "PRIORITY")
	private Integer priority;
	
	@Column(name = "STATUS")
	private String status;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "USER_ID")
	private User user;
}
