package com.veterinaria.pets.veterinariapets.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(value= "pets")
public class Pet {
    @Id
    private String id;
    private String namePet;
    private String parentsName;
    private String type;
    private String race;
    private Integer age;
    private String characteristicsPet;
    private String phone;
    private String address;
    private Integer state = 1;

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
