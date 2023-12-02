package com.example.restapimongojwt.models;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "Users")
public class User {

    @Id
    private String Id;

    private String email;
    private String password;


}
