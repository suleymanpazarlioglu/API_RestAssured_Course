package com.cydeo.pojo;



//{
//    "id": 15,
//    "name": "Meta",
//    "gender": "Female",
//    "phone": 1938695106
//}

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.PrimitiveIterator;
@Getter
@Setter
@ToString

@JsonIgnoreProperties(value = "id",allowSetters = true)

public class Spartan {

    private int id;
    private String name;
    private String gender;
    private long phone;


}
