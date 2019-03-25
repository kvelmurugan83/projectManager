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

import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.repository.ProjectRepository;

public class ProjectServiceTest {
	ProjectRepository repository = mock(ProjectRepository.class);
	ProjectService service;

	@Before
	public void setup() {
		service = new ProjectService(repository);
	}

	@Test
	public void testRetrieveAll() {
		Project project = Project.builder().build();
		when(repository.findAll()).thenReturn(Collections.singletonList(project));
		Iterable<Project> actual = service.findAll();
		verify(repository).findAll();
		actual.forEach(p -> {
			assertEquals(p, project);
		});
	}

	@Test
	public void testAdd() {
		Project expected = Project.builder().build();
		when(repository.save(expected)).thenReturn(expected);

		Project actual = service.add(expected);

		verify(repository).save(expected);
		assertEquals(actual, expected);
	}

	@Test
	public void testUpdate() {
		Project expected = Project.builder().build();
		when(repository.save(expected)).thenReturn(expected);

		Project actual = service.update(expected);

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

	@Test
	public void testDeleteAll() {
		service.deleteAll();

		verify(repository).deleteAll();
	}

	@Test
	public void testFindById() {
		Optional<Project> expected = Optional.of(Project.builder().projectId(Long.valueOf(1000)).build());
		when(repository.findById(Long.valueOf(1000))).thenReturn(expected);

		service.findById(Long.valueOf(1000));

		verify(repository).findById(Long.valueOf(1000));
	}

	@Test
	public void testfindByProjectName() {
		Project expected = Project.builder().build();
		when(repository.findByProject("TEST")).thenReturn(Collections.singletonList(expected));

		Project actual = service.findByProjectName("TEST");

		verify(repository).findByProject("TEST");
		assertEquals(actual, actual);
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
