package com.veterinaria.pets.veterinariapets.controller;



import com.veterinaria.pets.veterinariapets.model.Pet;
import com.veterinaria.pets.veterinariapets.services.PetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/veterinary/pet")
public class PetController {
    private final PetService petService;

    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping(value = "/all")
    public Flux<Pet> getAllPets() {
        return petService.getAllPets();
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pet>> findPetById(@PathVariable String id) {
        return petService.findPetById(id);
    }
    @PostMapping("/")
    public Mono<Pet> savePet(@RequestBody Pet pet) {
        return petService.savePet(pet);
    }

    @PutMapping("/updatePet/{id}")
    public Mono<ResponseEntity<Pet>> updatePet(@PathVariable(value = "id") String id, @RequestBody Pet pet) {
        return petService.updatePet(id,pet);
    }

    @PutMapping("/deleteLogicPet/{id}")
    public Mono<ResponseEntity<Pet>> deleteLogicPet(@PathVariable(value = "id") String id) {
        return petService.logicDeletePet(id);
    }
    @PatchMapping("/patchAge/{id}/{age}")
    public Mono<ResponseEntity<Pet>> updateAgePet(@PathVariable(value = "id") String id,@PathVariable(value="age")Integer age){
        return petService.updateAgePet(id,age);
    }
    @PatchMapping("/patchPhone/{id}/{phone}")
    public Mono<ResponseEntity<Pet>> updatePhonePet(@PathVariable(value = "id") String id, @PathVariable(value="phone")String phone){
        return petService.updatePhonePet(id,phone);
    }

    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Pet>> deletePet(@PathVariable(value = "id") String id){
        return petService.deletePet(id);
    }


}
