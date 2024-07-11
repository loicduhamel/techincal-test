package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.AbstractTest;
import fr.whitedev.technicaltest.repositories.AlbumRepository;
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
public class AlbumServiceTest extends AbstractTest {

	@Mock
	private AlbumRepository albumRepositoryMock;

	private AlbumService albumService;

	@BeforeAll
	public void init() throws JsonProcessingException {
		super.init();
	}

	@BeforeEach
	public void initService() {
		Mockito.reset(this.albumRepositoryMock);
		this.albumService = new AlbumService(this.albumRepositoryMock);
	}

	@Test
	public void shouldGetListWithAllAlbums() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbums(null)).hasSize(100);
	}

	@Test
	public void shouldGetListWithOneAlbum() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbums(1)).hasSize(1);
	}

	@Test
	public void shouldGetEmptyListIfAlbumIdIsNotFound() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbums(0)).isEmpty();
	}

	@Test
	public void shouldGetEmptyListIfNoData() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(Collections.emptyList());
		assertThat(this.albumService.getAlbums(null)).isEmpty();
	}

	@Test
	public void shouldGetEmptyListOnError() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenThrow(new JsonParseException(null, "error"));
		assertThat(this.albumService.getAlbums(null)).isEmpty();
	}

	@Test
	public void shouldGetListOfAlbumsOfUser() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbumsByUserId(1)).hasSize(10);
	}

	@Test
	public void shouldGetEmptyListIsUserIdIsNotFound() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbumsByUserId(0)).isEmpty();
	}

	@Test
	public void shouldGetListOfTitles() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getAlbumsTitle()).hasSize(100);
	}

	@Test
	public void shouldGetListOfUserIds() throws JsonProcessingException {
		when(this.albumRepositoryMock.getAlbums()).thenReturn(this.albums);
		assertThat(this.albumService.getUserIds()).hasSize(10);
	}
}
