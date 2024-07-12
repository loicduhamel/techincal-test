package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.AbstractTest;
import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import fr.whitedev.technicaltest.repositories.AlbumRepository;
import fr.whitedev.technicaltest.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataServiceTest extends AbstractTest {

	@Mock
	private UserService userServiceMock;

	@Mock
	private AlbumService albumServiceMock;

	private DataService dataService;

	private UserRepository userRepository;
	private AlbumRepository albumRepository;

	@BeforeAll
	public void init() throws JsonProcessingException {
		super.init();
	}

	@BeforeEach
	public void setUp() {
		Mockito.reset(this.userServiceMock);
		Mockito.reset(this.albumServiceMock);
		this.dataService = new DataService(this.userServiceMock, this.albumServiceMock);
		userRepository = new UserRepository();
		albumRepository = new AlbumRepository();
	}

	@Test
	void shouldGetUsers() throws JsonProcessingException {
		List<User> expectedUsers = userRepository.getUsers();
		when(userServiceMock.getUsers(null)).thenReturn(expectedUsers);

		List<User> actualUsers = dataService.getUsers(null);

		assertThat(actualUsers).isEqualTo(expectedUsers);
		verify(userServiceMock, times(1)).getUsers(null);
	}

	@Test
	void shouldGetUsersById() throws JsonProcessingException {
		List<User> expectedUsers = new ArrayList<>();
		expectedUsers.add(userRepository.getUsers().get(2));
		when(userServiceMock.getUsers(3)).thenReturn(expectedUsers);

		List<User> actualUsers = dataService.getUsers(3);

		assertThat(actualUsers).isEqualTo(expectedUsers);
		verify(userServiceMock, times(1)).getUsers(3);
	}

	@Test
	void shouldGetAlbums() throws JsonProcessingException {
		List<Album> expectedAlbums = albumRepository.getAlbums();
		when(albumServiceMock.getAlbums(null)).thenReturn(expectedAlbums);

		List<Album> actualAlbums = dataService.getAlbums(null);

		assertThat(actualAlbums).isEqualTo(expectedAlbums);
		verify(albumServiceMock, times(1)).getAlbums(null);
	}

	@Test
	void shouldGetAlbumsById() throws JsonProcessingException {
		List<Album> expectedAlbums = new ArrayList<>();
		expectedAlbums.add(albumRepository.getAlbums().get(0));
		when(albumServiceMock.getAlbums(1)).thenReturn(expectedAlbums);

		List<Album> actualAlbums = dataService.getAlbums(1);

		assertThat(actualAlbums).isEqualTo(expectedAlbums);
		verify(albumServiceMock, times(1)).getAlbums(1);
	}

	@Test
	public void shouldGetAlbumsByUsers() {
		when(this.userServiceMock.getUsers(null)).thenReturn(this.users);
		when(this.albumServiceMock.getAlbumsByUserId(anyInt())).thenReturn(Collections.singletonList(new Album(1, 1, "title")));

		List<UserAlbum> userAlbums = this.dataService.getAlbumsByUsers();
		assertThat(userAlbums).hasSize(10);
		for (UserAlbum userAlbum : userAlbums) {
			assertThat(userAlbum.getAlbums()).isNotEmpty();
		}
		verify(this.userServiceMock, times(1)).getUsers(null);
		verify(this.albumServiceMock, times(10)).getAlbumsByUserId(anyInt());
	}
}
