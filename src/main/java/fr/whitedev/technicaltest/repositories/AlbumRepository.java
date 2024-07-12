package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.Album;
import org.springframework.stereotype.Repository;

import java.util.List;

/*
Explain extends, @Repository, throws

- extends
extends is used for inheritance.
It indicates that a child class (AlbumRepository) inherits properties and methods from another parent class (AbstractRepository).
We use it to use the getBodyByFileName method which is common to AlbumRepository and UserRepository.

- @Repository
It is an annotation that is used to mark a class as a data repository.
A repository allows interactions with a data source, such as a database or JSON file in our case.

- throws
throws is used in method signatures to indicate exceptions that a method can throw.
These exceptions must be handled by the code calling the method.
Unlike a simple log.error which prints an error in the console, throws does the same, but stops the execution of the application.
*/

@Repository
public class AlbumRepository extends AbstractRepository {

	public List<Album> getAlbums() throws JsonProcessingException {
		return objectMapper.readValue(getBodyByFileName("albums.json"), objectMapper.getTypeFactory()
																					.constructCollectionType(List.class,
																						Album.class));
	}
}
