package com.veterinaria.pets.veterinariapets.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.validation.constraints.NotNull;

/**
 * Clase Pet el cual nos permite modelar la collecion Pets dentro de la base de datos
 * Mongodb Atlas
 */
@Data
@Document(value= "pets")
public class Pet {
    /**
     * Declaracion de los atributos de la clase pet y
     * sus respectivas validaciones
     */
    @Id
    private String id;
    @NotNull
    private String namePet;
    @NotNull
    private String parentsName;
    @NotNull
    private String type;
    @NotNull
    private String race;
    @NotNull
    private Integer age;
    private String characteristicsPet;
    @NotNull
    private String phone;
    @NotNull
    private String address;
    /**
     * Atributo state que nos permite realizar el borrado logico,
     * segun el sigueinte criterio
     * ->No visible para el usuario = 0
     * ->Visible para el usuario = 1
     * De esta manera cada vez que se declare un nuevo objeto de esta
     * clase por defecto estara visible para el usuario.
     */
    private Integer state = 1;

    /**
     * Contructor de la clase el cual recibe los siguientes parametros
     * @param id identificador creador por la base de datos
     * @param namePet nombre de la mascota
     * @param parentsName nombre de los familiares
     * @param type el tipo de animal
     * @param race la raza del animal
     * @param age la edad
     * @param characteristicsPet sus caracteristicas
     * @param phone telefono de contacto
     * @param address direccion
     */
    public Pet(String id, String namePet, String parentsName, String type, String race, Integer age, String characteristicsPet, String phone, String address) {
        this.id = id;
        this.namePet = namePet;
        this.parentsName = parentsName;
        this.type = type;
        this.race = race;
        this.age = age;
        this.characteristicsPet = characteristicsPet;
        this.phone = phone;
        this.address = address;
    }

}
