package fr.whitedev.technicaltest.controllers;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import fr.whitedev.technicaltest.services.DataService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/*
Explain @RestController, @GetMapping, why @Autowired is missing, why final class, why private attribute

- @RestController
It is an annotation that is used to mark a class as a REST API controller. It combines @Controller which manages web requests and @ResponseBody which converts the results into JSON.

- @GetMapping
It's an annotation that is used to map a method to an HTTP GET request with a specific path (example: /albums).

- Why @Autowired is missing?
The @Autowired annotation is used to inject dependencies. The annotation is not present because DataService is provided by the constructor with the Lombok @AllArgsConstructor annotation.

- Why final class?
A class declared as final prevents inheritance. It is also used to optimize the Java compiler.

- Why private attribute?
A class declared as private ensures that the class can only be accessed within the class where it is called. Which prevents accidental modification of the exterior.
*/

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
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
