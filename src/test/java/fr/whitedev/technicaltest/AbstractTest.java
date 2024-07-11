package fr.whitedev.technicaltest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import io.micrometer.core.instrument.util.IOUtils;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.InputStream;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@Tag("UnitTest")
public abstract class AbstractTest {

	protected List<Album> albums;
	protected List<User> users;

	protected void init() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		this.albums = objectMapper.readValue(getBodyByFileName("albums.json"), objectMapper.getTypeFactory()
																						   .constructCollectionType(List.class,
																							   Album.class));

		this.users = objectMapper.readValue(getBodyByFileName("users.json"), objectMapper.getTypeFactory()
																						 .constructCollectionType(List.class,
																							 User.class));
	}

	private String getBodyByFileName(String filename) {
		InputStream notFoundStream = AbstractTest.class.getClassLoader()
													   .getResourceAsStream(filename);
		return IOUtils.toString(notFoundStream, Charsets.UTF_8);
	}
}
