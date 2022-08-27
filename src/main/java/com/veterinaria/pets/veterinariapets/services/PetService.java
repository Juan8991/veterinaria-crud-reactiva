package com.veterinaria.pets.veterinariapets.services;


import com.veterinaria.pets.veterinariapets.model.Pet;
import com.veterinaria.pets.veterinariapets.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * En Petservice se declara toda la logica de nuestra aplicacion REST,
 * mediante la iyeccion del dependencias del PetRepository y la utilizacion
 * de Flux y Mono
 *
 */
@Service
public class PetService {
    /**
     * Se declara una constante Visible el cual nos permitira
     * guardar el valor Integer que representa la visibilidad de nuestros
     * Objetos.
     */
    private final Integer VISIBLE=1;
    private final PetRepository petRepository;

    /**
     * Inyeccion de depencias a traves del constructor
     * @param petRepository repositorio que va a inyectarse
     */
    @Autowired
    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    /**
     * Metodo que utiliza el petRepository para obtener un flujo de datos Flux que contiene
     * todos los pets que estan guardados en la base de datos que tienen un estado visible
     * es decir state=1.
     * @return Flujo de multiples datos
     */
    public Flux<Pet> getAllPets(){
        return petRepository.findAll().filter(pet -> pet.getState().equals(VISIBLE));
    }

    /**
     * Metodo que nos permite obtener a una mascota de la base de datos por su id.
     * @param id id del objeto pet de tipo String
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto y un estado HttpStatus.OK
     */
    public Mono<ResponseEntity<Pet>> findPetById(String id){
        return petRepository.findById(id)
                .filter(pet -> pet.getState().equals(VISIBLE))
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Metodo que permite guardar un nuevo objeto en la base de datos.
     * @param pet objeto de tipo Pet
     * @return una entidad de tipo responseEntity que si se guarda devolvera el objeto guardado
     * y un estado HttpStatus.OK
     */
    public Mono<ResponseEntity<Pet>> savePet(Pet pet){
        return petRepository.save(pet)
                .map(updatePet -> new ResponseEntity<>(updatePet, HttpStatus.OK));
    }

    /**
     * Metodo que permite actualizar un objeto mediante su id
     * @param id del objeto a actulizar
     * @param pet objeto con todos sus parametros
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
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

    /**
     * Metodo que permite realizar el borrado logico actulizando el estado de cada Pet
     * @param id del obejto a eleminar logicamente
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
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

    /**
     * Metodo que permite realizar el borrado fisico mediante el id del Pet
     * @param id del objeto que se quiere eliminar
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    public Mono<ResponseEntity<Pet>> deletePet(String id) {
        return petRepository.findById(id)
                .flatMap(deletedBook -> petRepository.delete(deletedBook)
                        .then(Mono.just(deletedBook)))
                .map(deletePet -> new ResponseEntity<>(deletePet, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Metodo que permite actulizar el atributo edad de Pet
     * @param id del objeto a actulizar
     * @param age del objeto
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
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

    /**
     * Metodo que permite actulizar el atributo phone de Pet
     * @param id del objeto a actulizar
     * @param phone del objeto
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
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
