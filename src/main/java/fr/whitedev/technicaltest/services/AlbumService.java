package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.repositories.AlbumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/*
Explain @Service, why @Autowired on constructor

- @Service
It is an annotation that is used to mark a class as a business service. The annotation allows Spring to inject the service into other beans like controllers.

- Why @Autowired on constructor?
The @Autowired annotation in the constructor allows Spring to automatically inject the dependencies needed for the service.
*/

@Service
public class AlbumService {

	private final AlbumRepository albumRepository;
	private List<Album> albums;

	@Autowired
	public AlbumService(AlbumRepository albumRepository) {
		this.albumRepository = albumRepository;
	}

	private List<Album> getAlbums() {
		if (this.albums == null) {
			try {
				this.albums = this.albumRepository.getAlbums();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				this.albums = Collections.emptyList();
			}
		}
		return this.albums;
	}

	public List<Album> getAlbums(Integer albumId) {
		List<Album> albums = getAlbums();
		if (albumId == null) {
			return albums;
		}
		return albums.stream()
				.filter(album -> album.getId() == albumId)
				.collect(Collectors.toList());
	}

	public List<Album> getAlbumsByUserId(int userId) {
		return getAlbums().stream()
				.filter(album -> album.getUserId() == userId)
				.collect(Collectors.toList());
	}

	public List<String> getAlbumsTitle() {
		return getAlbums().stream()
				.map(Album::getTitle)
				.collect(Collectors.toList());
	}

	public List<Integer> getUserIds() {
		return getAlbums().stream()
				.map(Album::getUserId)
				.distinct()
				.collect(Collectors.toList());
	}
}
