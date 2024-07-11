package fr.whitedev.technicaltest.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

	private final UserRepository userRepository;
	private List<User> users;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	private List<User> getUsers() {
		if (this.users == null) {
			try {
				this.users = this.userRepository.getUsers();
			} catch (JsonProcessingException e) {
				e.printStackTrace();
				this.users = Collections.emptyList();
			}
		}
		return this.users;
	}

	public List<User> getUsers(Integer userId) {
		List<User> users = getUsers();
		if (userId == null) {
			return users;
		}
		return users.stream()
				.filter(user -> user.getId() == userId)
				.collect(Collectors.toList());
	}
}
