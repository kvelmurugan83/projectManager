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

import com.cts.fse.projectmanager.entity.Task;
import com.cts.fse.projectmanager.repository.TaskRepository;

public class TaskServiceTest {

	TaskRepository repository = mock(TaskRepository.class);
	TaskService service;

	@Before
	public void setup() {
		service = new TaskService(repository);
	}

	@Test
	public void testRetrieveAll() {
		Task expected = Task.builder().build();
		when(repository.findAll()).thenReturn(Collections.singletonList(expected));
		Iterable<Task> actual = service.findAll();
		verify(repository).findAll();
		actual.forEach(t -> {
			assertEquals(t, expected);
		});
	}

	@Test
	public void testAdd() {
		Task expected = Task.builder().build();
		when(repository.save(expected)).thenReturn(expected);

		Task actual = service.add(expected);

		verify(repository).save(expected);
		assertEquals(actual, expected);
	}

	@Test
	public void testUpdate() {
		Task expected = Task.builder().build();
		when(repository.save(expected)).thenReturn(expected);

		Task actual = service.update(expected);

		verify(repository).save(expected);
		assertEquals(actual, expected);
	}

	@Test
	public void testDelete() {
		when(repository.existsById(Long.valueOf(1000))).thenReturn(true);

		service.delete(Long.valueOf(1000));

		verify(repository).existsById(Long.valueOf(1000));
		verify(repository).deleteById(Long.valueOf(1000));
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

	@Test
	public void testDeleteAll() {
		service.deleteAll();

		verify(repository).deleteAll();
	}

	@Test
	public void testFindById() {
		Optional<Task> expected = Optional.of(Task.builder().taskId(Long.valueOf(1000)).build());
		when(repository.findById(Long.valueOf(1000))).thenReturn(expected);

		service.findById(Long.valueOf(1000));

		verify(repository).findById(Long.valueOf(1000));
	}
}
