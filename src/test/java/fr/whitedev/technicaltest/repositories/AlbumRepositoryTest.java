package fr.whitedev.technicaltest.repositories;

import com.fasterxml.jackson.core.type.TypeReference;
import fr.whitedev.technicaltest.models.Album;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AlbumRepositoryTest extends AbstractRepository {

    private AlbumRepository albumRepository;

    @BeforeEach
    void setUp() {
        albumRepository = new AlbumRepository();
    }

    @Test
    void shouldGetAlbums() throws IOException {
        String json = loadJsonFromFile();

        List<Album> expectedAlbums = objectMapper.readValue(json, new TypeReference<List<Album>>() {});

        List<Album> actualAlbums = albumRepository.getAlbums();

        assertNotNull(actualAlbums);
        assertEquals(expectedAlbums.size(), actualAlbums.size());

        for (int i = 0; i < expectedAlbums.size(); i++) {
            assertEquals(expectedAlbums.get(i).getId(), actualAlbums.get(i).getId());
            assertEquals(expectedAlbums.get(i).getUserId(), actualAlbums.get(i).getUserId());
            assertEquals(expectedAlbums.get(i).getTitle(), actualAlbums.get(i).getTitle());
        }
    }

    private String loadJsonFromFile() throws IOException {
        ClassPathResource resource = new ClassPathResource("albums.json");
        byte[] bdata = FileCopyUtils.copyToByteArray(resource.getInputStream());
        return new String(bdata, StandardCharsets.UTF_8);
    }
}
