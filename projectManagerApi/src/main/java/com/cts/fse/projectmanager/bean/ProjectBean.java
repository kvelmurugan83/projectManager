package com.cts.fse.projectmanager.bean;

import java.io.Serializable;
import java.time.LocalDate;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class ProjectBean implements Serializable {

	private static final long serialVersionUID = 20190121;

	private Long id;

	private String project;

	private LocalDate startDate;

	private LocalDate endDate;

	private Integer priority;

	private Boolean suspend;

	private UserBean manager;

	private int noOfTasks;
}
