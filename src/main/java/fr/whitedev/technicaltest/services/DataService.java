package fr.whitedev.technicaltest.services;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.models.UserAlbum;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public final class DataService {

	private UserService userService;
	private AlbumService albumService;

	public List<User> getUsers(Integer userId) {
		return userService.getUsers(userId);
	}

	public List<Album> getAlbums(Integer albumId) {
		return albumService.getAlbums(albumId);
	}

	public List<UserAlbum> getAlbumsByUsers() {
		List<User> users = userService.getUsers(null);

		return users.stream()
				.map(user -> {
					List<String> userAlbums = albumService.getAlbumsByUserId(user.getId())
							.stream()
							.map(Album::getTitle)
							.collect(Collectors.toList());
					return new UserAlbum(user.getUsername(), userAlbums);
				})
				.collect(Collectors.toList());
	}
}
