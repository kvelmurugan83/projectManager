package com.cts.fse.projectmanager.bean;

import java.io.Serializable;

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
public class UserBean implements Serializable {

	private static final long serialVersionUID = 20190121;
	
	private Long id;
		
	private String firstName;
	
	private String lastName;
	
	private Long empId;

}
