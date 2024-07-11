package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.AbstractTest;
import fr.whitedev.technicaltest.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserServiceTest extends AbstractTest {

	@Mock
	private UserRepository userRepositoryMock;

	private UserService userService;

	@BeforeAll
	public void init() throws JsonProcessingException {
		super.init();
	}

	@BeforeEach
	public void initService() {
		Mockito.reset(this.userRepositoryMock);
		this.userService = new UserService(this.userRepositoryMock);
	}

	@Test
	public void shouldGetListWithAllUsers() throws JsonProcessingException {
		when(this.userRepositoryMock.getUsers()).thenReturn(this.users);
		assertThat(this.userService.getUsers(null)).hasSize(10);
	}

	@Test
	public void shouldGetListWithOneUser() throws JsonProcessingException {
		when(this.userRepositoryMock.getUsers()).thenReturn(this.users);
		assertThat(this.userService.getUsers(1)).hasSize(1);
	}

	@Test
	public void shouldGetEmptyListIfUserIdIsNotFound() throws JsonProcessingException {
		when(this.userRepositoryMock.getUsers()).thenReturn(this.users);
		assertThat(this.userService.getUsers(0)).isEmpty();
	}

	@Test
	public void shouldGetEmptyListIfNoData() throws JsonProcessingException {
		when(this.userRepositoryMock.getUsers()).thenReturn(Collections.emptyList());
		assertThat(this.userService.getUsers(null)).isEmpty();
	}

	@Test
	public void shouldGetEmptyListOnError() throws JsonProcessingException {
		when(this.userRepositoryMock.getUsers()).thenThrow(new JsonParseException(null, "error"));
		assertThat(this.userService.getUsers(null)).isEmpty();
	}
}
