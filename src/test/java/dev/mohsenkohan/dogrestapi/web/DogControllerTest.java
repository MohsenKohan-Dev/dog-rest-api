package dev.mohsenkohan.dogrestapi.web;

import dev.mohsenkohan.dogrestapi.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = DogController.class)
class DogControllerTest {

    @MockBean
    DogService dogService;

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllDogs() throws Exception {
        mockMvc.perform(get("/dogs").with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogs();
    }

    @Test
    void getDogBreeds() throws Exception {
        mockMvc.perform(get("/dogs/breed").with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogBreeds();
    }

    @Test
    void getBreedById() throws Exception {
        when(dogService.retrieveDogBreedById(1L)).thenReturn("Husky");

        mockMvc.perform(get("/1/breed").with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(content().contentType("text/plain;charset=UTF-8"))
                .andExpect(content().string("Husky"));

        verify(dogService, times(1)).retrieveDogBreedById(1L);
    }

    @Test
    void getDogNames() throws Exception {
        mockMvc.perform(get("/dogs/name").with(httpBasic("admin", "admin")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().json("[]"));

        verify(dogService, times(1)).retrieveDogNames();
    }
}