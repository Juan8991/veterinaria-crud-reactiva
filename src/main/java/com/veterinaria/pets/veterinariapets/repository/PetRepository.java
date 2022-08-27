package com.veterinaria.pets.veterinariapets.repository;


import com.veterinaria.pets.veterinariapets.model.Pet;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Interfaz repository la cual nois permite implementar los metodos de ReactiveMongoRepository
 * la cual recibe nuestro model y el tipo de Id de este.
 */
@Repository
public interface PetRepository extends ReactiveMongoRepository<Pet,String> {


}
