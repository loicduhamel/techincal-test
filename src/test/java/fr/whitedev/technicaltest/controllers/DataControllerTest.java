package fr.whitedev.technicaltest.controllers;

import fr.whitedev.technicaltest.models.Album;
import fr.whitedev.technicaltest.models.User;
import fr.whitedev.technicaltest.services.DataService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(DataController.class)
class DataControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DataService dataService;

    @Test
    void shouldCallGetAlbumsIfHttpRequestIsGetAlbums() throws Exception {
        when(dataService.getAlbums(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/albums")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallGetAlbumsByIdIfHttpRequestIsGetAlbumsWithId() throws Exception {
        int albumId = 1;
        Album album = new Album();
        when(dataService.getAlbums(albumId)).thenReturn(Collections.singletonList(album));

        mockMvc.perform(get("/albums/{id}", albumId)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallGetUsersIfHttpRequestIsGetUsers() throws Exception {
        when(dataService.getUsers(null)).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallGetUsersByIdIfHttpRequestIsGetUsersWithId() throws Exception {
        int userId = 1;
        User user = new User();
        when(dataService.getUsers(userId)).thenReturn(Collections.singletonList(user));

        mockMvc.perform(get("/users/{id}", userId)
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void shouldCallGetAlbumsByUsersIfHttpRequestIsGetAlbumsByUsers() throws Exception {
        when(dataService.getAlbumsByUsers()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/users/albums")
                        .contentType(org.springframework.http.MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
