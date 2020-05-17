package dev.mohsenkohan.dogrestapi;

import dev.mohsenkohan.dogrestapi.entity.Dog;
import dev.mohsenkohan.dogrestapi.service.DogService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DogRestApiApplicationTests {

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    DogService dogService;

    @Test
    void getAllDogs() {
        ResponseEntity<Dog[]> response =
                restTemplate
                        .withBasicAuth("admin", "admin")
                        .getForEntity("http://localhost:" + port + "/dogs", Dog[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dogService.retrieveDogs(), Arrays.asList(response.getBody()));
    }

    @Test
    void getDogBreeds() {
        ResponseEntity<String[]> response =
                restTemplate
                        .withBasicAuth("admin", "admin")
                        .getForEntity("http://localhost:" + port + "/dogs/breed", String[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dogService.retrieveDogBreeds(), Arrays.asList(response.getBody()));

    }

    @Test
    void getBreedById() {
        ResponseEntity<String> response =
                restTemplate
                        .withBasicAuth("admin", "admin")
                        .getForEntity("http://localhost:" + port + "/{id}/breed",
                                String.class, 1);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dogService.retrieveDogBreedById(1L), response.getBody());
    }

    @Test
    void getDogNames() {
        ResponseEntity<String[]> response =
                restTemplate
                        .withBasicAuth("admin", "admin")
                        .getForEntity("http://localhost:" + port + "/dogs/name", String[].class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(dogService.retrieveDogNames(), Arrays.asList(response.getBody()));
    }
}
