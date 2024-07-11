package fr.whitedev.technicaltest.controllers;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import fr.whitedev.technicaltest.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// TODO: explain @RestController, @GetMapping, why @Autowired is missing, why final class, why private attribute

@RestController
@AllArgsConstructor
public final class DataController {

	private DataService dataService;

	@GetMapping(value = { "/albums", "/albums/{id}" })
	public List<Album> getAlbums(@PathVariable(required = false) Integer id) {
		return this.dataService.getAlbums(id);
	}

	@GetMapping(value = { "/users", "/users/{id}" })
	public List<User> getUsers(@PathVariable(required = false) Integer id) {
		return this.dataService.getUsers(id);
	}

	@GetMapping("/users/albums")
	public List<UserAlbum> getAlbumsByUsers() {
		return this.dataService.getAlbumsByUsers();
	}
}
