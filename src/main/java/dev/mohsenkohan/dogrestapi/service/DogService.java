package dev.mohsenkohan.dogrestapi.service;

import dev.mohsenkohan.dogrestapi.entity.Dog;

import java.util.List;

public interface DogService {

    List<Dog> retrieveDogs();

    List<String> retrieveDogBreeds();

    String retrieveDogBreedById(Long id);

    List<String> retrieveDogNames();
}
