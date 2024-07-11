package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository extends AbstractRepository {

	public List<User> getUsers() throws JsonProcessingException {
		return objectMapper.readValue(getBodyByFileName("users.json"), objectMapper.getTypeFactory()
																			.constructCollectionType(List.class,
																				User.class));
	}
}
