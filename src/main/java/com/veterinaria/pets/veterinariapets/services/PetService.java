package com.veterinaria.pets.veterinariapets.services;


import com.veterinaria.pets.veterinariapets.model.Pet;
import com.veterinaria.pets.veterinariapets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PetService {
    private final Integer VISIBLE=1;
    private final PetRepository petRepository;
    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }
    public Flux<Pet> getAllPets(){
        return petRepository.findAll()
                .filter(pet -> pet.getState().equals(VISIBLE));
    }
    public Mono<ResponseEntity<Pet>> findPetById(String id){
        return petRepository.findById(id)
                .filter(pet -> pet.getState().equals(VISIBLE))
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public Mono<Pet> savePet(Pet pet){
        return petRepository.save(pet);
    }
    public Mono<ResponseEntity<Pet>> updatePet(String id, Pet pet) {
        return petRepository.findById(id)
                .filter(filterPet -> filterPet.getState().equals(VISIBLE))
                .flatMap(originalPet -> {
                    originalPet.setNamePet(pet.getNamePet());
                    originalPet.setParentsName(pet.getParentsName());
                    originalPet.setType(pet.getType());
                    originalPet.setRace(pet.getRace());
                    originalPet.setAge(pet.getAge());
                    originalPet.setCharacteristicsPet(pet.getCharacteristicsPet());
                    originalPet.setPhone(pet.getType());
                    originalPet.setAddress(pet.getAddress());
                    return petRepository.save(originalPet);
                })
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public Mono<ResponseEntity<Pet>> logicDeletePet(String id) {
        final Integer INVISIBLE = 0;
        return petRepository.findById(id)
                .filter(filterPet -> filterPet.getState().equals(VISIBLE))
                .flatMap(originalPet -> {
                    originalPet.setState(INVISIBLE);
                    return petRepository.save(originalPet);
                })
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    public Mono<ResponseEntity<Pet>> deletePet(String id) {
        return petRepository.findById(id)
                .flatMap(deletedBook -> petRepository.delete(deletedBook)
                        .then(Mono.just(deletedBook)))
                .map(deletePet -> new ResponseEntity<>(deletePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Pet>> updateAgePet(String id, Integer age) {
        return petRepository.findById(id)
                .filter(filterPet -> filterPet.getState().equals(VISIBLE))
                .flatMap(originalPet -> {
                    originalPet.setAge(age);
                    return petRepository.save(originalPet);
                })
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public Mono<ResponseEntity<Pet>> updatePhonePet(String id, String phone) {
        return petRepository.findById(id)
                .filter(filterPet -> filterPet.getState().equals(VISIBLE))
                .flatMap(originalPet -> {
                    originalPet.setPhone(phone);
                    return petRepository.save(originalPet);
                })
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
