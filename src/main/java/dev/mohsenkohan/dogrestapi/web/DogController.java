package dev.mohsenkohan.dogrestapi.web;

import dev.mohsenkohan.dogrestapi.entity.Dog;
import dev.mohsenkohan.dogrestapi.service.DogService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(responseCode = "400", description = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(responseCode = "401", description = "Due to security constraints, your access request cannot be authorized."),
        @ApiResponse(responseCode = "500", description = "The server is down. Please make sure that the Location microservice is running.")
})
public class DogController {

    private final DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getAllDogs() {
        List<Dog> dogs = dogService.retrieveDogs();
        return new ResponseEntity<>(dogs, HttpStatus.OK);
    }

    @GetMapping("/dogs/breed")
    public ResponseEntity<List<String>> getDogBreeds() {
        List<String> breeds = dogService.retrieveDogBreeds();
        return new ResponseEntity<>(breeds, HttpStatus.OK);
    }

    @GetMapping("/{id}/breed")
    public ResponseEntity<String> getBreedById(@PathVariable Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<>(breed, HttpStatus.OK);
    }

    @GetMapping("/dogs/name")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> names = dogService.retrieveDogNames();
        return new ResponseEntity<>(names, HttpStatus.OK);
    }
}
