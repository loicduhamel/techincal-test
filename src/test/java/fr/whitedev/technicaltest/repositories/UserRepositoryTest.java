package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import fr.whitedev.technicaltest.models.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserRepositoryTest extends AbstractRepository {

    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepository();
    }

    @Test
    void shouldGetUsers() throws IOException {
        String json = loadJsonFromFile();

        List<User> expectedUsers = objectMapper.readValue(json, new TypeReference<List<User>>() {});

        List<User> actualUsers = userRepository.getUsers();

        assertNotNull(actualUsers);
        assertEquals(expectedUsers.size(), actualUsers.size());

        for (int i = 0; i < expectedUsers.size(); i++) {
            User expectedUser = expectedUsers.get(i);
            User actualUser = actualUsers.get(i);

            assertEquals(expectedUser.getId(), actualUser.getId());
            assertEquals(expectedUser.getName(), actualUser.getName());
            assertEquals(expectedUser.getUsername(), actualUser.getUsername());
            assertEquals(expectedUser.getEmail(), actualUser.getEmail());

            assertEquals(expectedUser.getAddress().getStreet(), actualUser.getAddress().getStreet());
            assertEquals(expectedUser.getAddress().getSuite(), actualUser.getAddress().getSuite());
            assertEquals(expectedUser.getAddress().getCity(), actualUser.getAddress().getCity());
            assertEquals(expectedUser.getAddress().getZipcode(), actualUser.getAddress().getZipcode());
            assertEquals(expectedUser.getAddress().getGeo().getLat(), actualUser.getAddress().getGeo().getLat());
            assertEquals(expectedUser.getAddress().getGeo().getLng(), actualUser.getAddress().getGeo().getLng());

            assertEquals(expectedUser.getPhone(), actualUser.getPhone());
            assertEquals(expectedUser.getWebsite(), actualUser.getWebsite());

            assertEquals(expectedUser.getCompany().getName(), actualUser.getCompany().getName());
            assertEquals(expectedUser.getCompany().getCatchPhrase(), actualUser.getCompany().getCatchPhrase());
            assertEquals(expectedUser.getCompany().getBs(), actualUser.getCompany().getBs());
        }
    }

    private String loadJsonFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("users.json");
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bdata, StandardCharsets.UTF_8);
    }
}