package com.example.restapimongojwt.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
@Document(collection = "Users")
public class User {

    @Id
    private String Id;


    private String name;

    @Email
    private String email;
    private String password;


}
