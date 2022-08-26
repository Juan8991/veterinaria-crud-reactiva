package com.veterinaria.pets.veterinariapets.repository;


import com.veterinaria.pets.veterinariapets.model.Pet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PetRepository extends ReactiveMongoRepository<Pet,String> {


}
