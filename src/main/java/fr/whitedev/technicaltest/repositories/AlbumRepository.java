package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

// TODO: explain extends, @Repository, throws

@Repository
public class AlbumRepository extends AbstractRepository {

	public List<Album> getAlbums() throws JsonProcessingException {
		return objectMapper.readValue(getBodyByFileName("albums.json"), objectMapper.getTypeFactory()
																					.constructCollectionType(List.class,
																						Album.class));
	}
}
