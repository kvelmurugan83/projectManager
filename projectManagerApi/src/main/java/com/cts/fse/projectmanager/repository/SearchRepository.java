package com.cts.fse.projectmanager.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class SearchRepository {

	@PersistenceContext
	private EntityManager em;
}
