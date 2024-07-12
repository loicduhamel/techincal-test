package fr.whitedev.technicaltest.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.util.ResourceUtils;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class AbstractRepositoryTest {

    private static class TestRepository extends AbstractRepository {}

    private final TestRepository testRepository = new TestRepository();

    @Test
    void shouldGetBodyByFileName() throws Exception {
        String expectedContent = new String(Files.readAllBytes(
                Paths.get(ResourceUtils.getFile("classpath:albums.json").toURI())), StandardCharsets.UTF_8);

        String actualContent = testRepository.getBodyByFileName("albums.json");

        assertNotNull(actualContent);
        assertEquals(expectedContent, actualContent);
    }
}
