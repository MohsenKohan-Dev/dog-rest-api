package dev.mohsenkohan.dogrestapi.service;

import dev.mohsenkohan.dogrestapi.entity.Dog;
import dev.mohsenkohan.dogrestapi.repository.DogRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    private final DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public List<Dog> retrieveDogs() {
        return (List<Dog>) dogRepository.findAll();
    }

    @Override
    public List<String> retrieveDogBreeds() {
        return dogRepository.findAllBreeds();
    }

    @Override
    public String retrieveDogBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        return optionalBreed.orElseThrow(DogNotFoundException::new);
    }

    @Override
    public List<String> retrieveDogNames() {
        return dogRepository.findAllNames();
    }
}
