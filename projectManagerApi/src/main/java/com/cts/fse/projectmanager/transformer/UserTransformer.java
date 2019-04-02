package com.cts.fse.projectmanager.transformer;

import com.cts.fse.projectmanager.bean.UserBean;
import com.cts.fse.projectmanager.entity.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserTransformer {

	public static User toEntity(UserBean b) {
		log.info("toEntity  " + b);
		User u = new User();
		u.setEmpId(b.getEmpId());
		u.setFirstName(b.getFirstName());
		u.setLastName(b.getLastName());
		u.setId(b.getId());
		return u;
	}
	
	public static UserBean toBean(User b) {
		log.info("toEntity  " + b);
		UserBean u = new UserBean();
		u.setEmpId(b.getEmpId());
		u.setFirstName(b.getFirstName());
		u.setLastName(b.getLastName());
		u.setId(b.getId());
		return u;
	}
}
