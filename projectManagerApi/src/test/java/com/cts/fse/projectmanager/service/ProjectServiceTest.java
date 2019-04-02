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

import com.cts.fse.projectmanager.bean.ProjectBean;
import com.cts.fse.projectmanager.entity.Project;
import com.cts.fse.projectmanager.repository.ProjectRepository;
import com.cts.fse.projectmanager.transformer.ProjectTransformer;

public class ProjectServiceTest {
	ProjectRepository repository = mock(ProjectRepository.class);
	ProjectService service;
	private UserService userService = mock(UserService.class);;

	private TaskService taskService = mock(TaskService.class);;

	@Before
	public void setup() {
		service = new ProjectService(repository, userService, taskService);
	}

	@Test
	public void testRetrieveAll() {
		ProjectBean project = ProjectBean.builder().project("Project").build();
		when(repository.findAll()).thenReturn(Collections.singletonList(ProjectTransformer.toEntity(project)));
		List<ProjectBean> actual = service.findAll();
		verify(repository).findAll();
		actual.forEach(p -> {
			assertEquals(p.getProject(), "Project");
		});
	}

	@Test
	public void testAdd() {
		ProjectBean expected = ProjectBean.builder().build();
		when(repository.save(ProjectTransformer.toEntity(expected))).thenReturn(ProjectTransformer.toEntity(expected));

		ProjectBean actual = service.add(expected);

		verify(repository).save(ProjectTransformer.toEntity(expected));
		assertEquals(actual, expected);
	}

	@Test
	public void testUpdate() {
		ProjectBean expected = ProjectBean.builder().build();
		when(repository.save(ProjectTransformer.toEntity(expected))).thenReturn(ProjectTransformer.toEntity(expected));
		
		ProjectBean actual = service.update(expected);

		verify(repository).save(ProjectTransformer.toEntity(expected));
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
		Optional<Project> expected = Optional.of(Project.builder().id(Long.valueOf(1000)).build());
		when(repository.findById(Long.valueOf(1000))).thenReturn(expected);

		service.findById(Long.valueOf(1000));

		verify(repository).findById(Long.valueOf(1000));
	}

	@Test
	public void testfindByProjectName() {
		Project expected = Project.builder().project("T").build();
		when(repository.findByProject("TEST")).thenReturn(Collections.singletonList(expected));

		ProjectBean actual = service.findByProjectName("TEST");

		verify(repository).findByProject("TEST");
		assertEquals(actual.getProject(), "T");
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
