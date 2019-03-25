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

@Builder
@Data	
@Entity
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
public class Project implements Serializable {

	private static final long serialVersionUID = 20190121;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROJECT_ID")
	private Long projectId;
	
	@Column(name = "PROJECT")
	private String project;
	
	@Column(name = "START_DATE")
	private LocalDate startDate;
	
	@Column(name = "END_DATE")
	private LocalDate endDate;
	
	@Column(name = "PRIORITY")
	private Integer priority;
	
	@OneToOne(fetch = FetchType.LAZY, optional = true)
	@JoinColumn(name = "MANAGER_ID")
	private User manager;
}
