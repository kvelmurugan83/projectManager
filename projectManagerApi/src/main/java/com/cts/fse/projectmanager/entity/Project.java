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
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Project implements Serializable {

	private static final long serialVersionUID = 20190121;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PROJECT_ID")
	private Long id;

	@Column(name = "PROJECT")
	private String project;

	@Column(name = "START_DATE", nullable = true)
	private LocalDate startDate;

	@Column(name = "END_DATE", nullable = true)
	private LocalDate endDate;

	@Column(name = "PRIORITY")
	private Integer priority;
	
	@Column(name = "SUSPEND", nullable = true)
	private Boolean suspend;

	@OneToOne(fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "MANAGER_ID")
	private User manager;
	
	@Transient
	private int noOfTasks;
}
