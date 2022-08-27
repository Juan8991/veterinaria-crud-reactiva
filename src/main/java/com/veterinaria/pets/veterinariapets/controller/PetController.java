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

import javax.validation.Valid;

/**
 * Esta clase nos permite exponer nuestro apiRest para ser
 * consumida mediente el endpoint base /veterinary/pet
 * utilizando la capa de Servicio PetService
 */
@CrossOrigin(value = "*")
@RestController
@RequestMapping(value = "/veterinary/pet")
public class PetController {
    private final PetService petService;
    /**
     * Inyeccion de depencias a traves del constructor
     * @param petService servicio que va a inyectarse
     */
    @Autowired
    public PetController(PetService petService) {
        this.petService = petService;
    }

    /**
     * Obtiene mediante la peticion Get los objetos Pet de la base de datos
     * @return Flujo de multiples datos
     */
    @GetMapping(value = "/all")
    public Flux<Pet> getAllPets() {
        return petService.getAllPets();
    }
    /**
     * Metodo que nos permite obtener a una mascota de la base de datos por su id.
     * @PathVariable id id del objeto pet de tipo String
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto y un estado HttpStatus.OK
     */
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Pet>> findPetById(@Valid @PathVariable String id) {
        return petService.findPetById(id);
    }
    /**
     * Metodo que permite guardar un nuevo objeto en la base de datos.
     * @RequestBody pet objeto de tipo Pet
     * @return una entidad de tipo responseEntity que si se guarda devolvera el objeto guardado
     * y un estado HttpStatus.OK
     */
    @PostMapping("/")
    public Mono<ResponseEntity<Pet>> savePet(@Valid @RequestBody Pet pet) {
        return petService.savePet(pet);
    }
    /**
     * Metodo que permite actualizar un objeto mediante su id
     * @PathVariable id del objeto a actulizar
     * @RequestBody pet objeto con todos sus parametros
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    @PutMapping("/updatePet/{id}")
    public Mono<ResponseEntity<Pet>> updatePet(@Valid @PathVariable(value = "id") String id,@Valid @RequestBody Pet pet) {
        return petService.updatePet(id,pet);
    }
    /**
     * Metodo que permite realizar el borrado logico actulizando el estado de cada Pet
     * @PathVariable id del obejto a eleminar logicamente
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    @PutMapping("/deleteLogicPet/{id}")
    public Mono<ResponseEntity<Pet>> deleteLogicPet(@Valid @PathVariable(value = "id") String id) {
        return petService.logicDeletePet(id);
    }
    /**
     * Metodo que permite actulizar el atributo phone de Pet
     * @PathVariable id del objeto a actulizar
     * @PathVariable phone del objeto
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    @PatchMapping("/patchAge/{id}/{age}")
    public Mono<ResponseEntity<Pet>> updateAgePet(@Valid @PathVariable(value = "id") String id,@Valid @PathVariable(value="age")Integer age){
        return petService.updateAgePet(id,age);
    }
    /**
     * Metodo que permite actulizar el atributo edad de Pet
     * @PathVariable id del objeto a actulizar
     * @PathVariable age del objeto
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    @PatchMapping("/patchPhone/{id}/{phone}")
    public Mono<ResponseEntity<Pet>> updatePhonePet(@Valid @PathVariable(value = "id") String id,@Valid @PathVariable(value="phone")String phone){
        return petService.updatePhonePet(id,phone);
    }
    /**
     * Metodo que permite realizar el borrado fisico mediante el id del Pet
     * @PathVariable id del objeto que se quiere eliminar
     * @return una entidad de tipo responseEntity que si se encuentra vacia devolvera un estado HttpStatus.NOT_FOUND,
     * pero si se encuentra devolvera un responseEntity con el objeto ACTUALIZADO y un estado HttpStatus.OK
     */
    @DeleteMapping("/delete/{id}")
    public Mono<ResponseEntity<Pet>> deletePet(@Valid@PathVariable(value = "id") String id){
        return petService.deletePet(id);
    }


}
