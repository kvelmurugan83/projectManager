package com.cts.fse.projectmanager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.cts.fse.projectmanager.bean.UserBean;
import com.cts.fse.projectmanager.entity.User;
import com.cts.fse.projectmanager.repository.UserRepository;
import com.cts.fse.projectmanager.transformer.UserTransformer;

public class UserServiceTest {

	UserRepository repository = mock(UserRepository.class);
	UserService service;

	@Before
	public void setup() {
		service = new UserService(repository);
	}

	@Test
	public void testRetrieveAllUser() {
		UserBean user = UserBean.builder().build();
		User u = UserTransformer.toEntity(user);
		when(repository.findAll()).thenReturn(Collections.singletonList(u));
		List<UserBean> actual = service.findAll();
		verify(repository).findAll();
		actual.forEach(o -> {
			assertEquals(o, user);
		});
	}

	@Test
	public void testAdd() {
		UserBean user = UserBean.builder().build();
		User u = UserTransformer.toEntity(user);
		when(repository.save(u)).thenReturn(u);
		
		UserBean actual = service.add(user);
		
		verify(repository).save(u);
		assertEquals(actual, user);
	}
	
	@Test
	public void testUpdate() {
		UserBean user = UserBean.builder().build();
		User u = UserTransformer.toEntity(user);
		when(repository.save(u)).thenReturn(u);
		
		UserBean actual = service.update(user);
		
		verify(repository).save(u);
		assertEquals(actual, user);
	}
	
	@Test
	public void testDelete() {
		when(repository.existsById(Long.valueOf(1000))).thenReturn(true);
		
		service.delete(Long.valueOf(1000));
		
		verify(repository).existsById(Long.valueOf(1000));
		verify(repository).deleteById(Long.valueOf(1000));		
	}
	
	@Test
	public void testDeleteAll() {
		service.deleteAll();
		
		verify(repository).deleteAll();
	}
	
	
	@Test
	public void testFindById() {
		Optional<User> user = Optional.of(User.builder().id(Long.valueOf(1000)).build());
		when(repository.findById(Long.valueOf(1000))).thenReturn(user);
		
		service.findById(Long.valueOf(1000));
		
		verify(repository).findById(Long.valueOf(1000));
	}
	
	@Test
	public void testFindByEmpId() {
		UserBean user = UserBean.builder().id(Long.valueOf(1000)).build();
		User u = UserTransformer.toEntity(user);
		when(repository.findByEmpId(Long.valueOf(1000))).thenReturn(Collections.singletonList(u));
		
		UserBean actual = service.findByEmpId(Long.valueOf(1000));
		
		verify(repository).findByEmpId(Long.valueOf(1000));
		assertEquals(actual, user);
	}
	
	@Test(expected = EntityNotFoundException.class)
	public void testDeleteFail() {
		Long id = new Long(1);
		when(repository.existsById(id)).thenReturn(false);

		service.delete(id);
	}

	@Test(expected = EntityNotFoundException.class)
	public void testFindByIdFail() {
		Long id = new Long(1);
		when(repository.findById(id)).thenReturn(Optional.empty());

		service.findById(id);
	}
}
