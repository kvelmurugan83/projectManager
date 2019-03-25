package com.cts.fse.projectmanager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.junit.Before;
import org.junit.Test;

import com.cts.fse.projectmanager.entity.User;
import com.cts.fse.projectmanager.repository.UserRepository;

public class UserServiceTest {

	UserRepository repository = mock(UserRepository.class);
	UserService service;

	@Before
	public void setup() {
		service = new UserService(repository);
	}

	@Test
	public void testRetrieveAllUser() {
		User user = User.builder().build();
		when(repository.findAll()).thenReturn(Collections.singletonList(user));
		Iterable<User> actual = service.findAll();
		verify(repository).findAll();
		actual.forEach(u -> {
			assertEquals(u, user);
		});
	}

	@Test
	public void testAdd() {
		User user = User.builder().build();
		when(repository.save(user)).thenReturn(user);
		
		User actual = service.add(user);
		
		verify(repository).save(user);
		assertEquals(actual, user);
	}
	
	@Test
	public void testUpdate() {
		User user = User.builder().build();
		when(repository.save(user)).thenReturn(user);
		
		User actual = service.update(user);
		
		verify(repository).save(user);
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
		Optional<User> user = Optional.of(User.builder().userId(Long.valueOf(1000)).build());
		when(repository.findById(Long.valueOf(1000))).thenReturn(user);
		
		service.findById(Long.valueOf(1000));
		
		verify(repository).findById(Long.valueOf(1000));
	}
	
	@Test
	public void testFindByEmpId() {
		User user = User.builder().userId(Long.valueOf(1000)).build();
		when(repository.findByEmpId(Long.valueOf(1000))).thenReturn(Collections.singletonList(user));
		
		User actual = service.findByEmpId(Long.valueOf(1000));
		
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
