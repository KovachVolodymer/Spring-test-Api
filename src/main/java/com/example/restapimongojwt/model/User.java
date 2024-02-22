package com.example.restapimongojwt.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashSet;
import java.util.Set;


@Data
@Document(collection = "Users")
public class User {

    @Id
    private String id;


    private String name;
    private String email;
    private String password;
    private Set<String> roles = new HashSet<>();

    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles.add("ROLE_USER"); // Додаємо роль "USER" за замовчуванням
    }

    public User() {
    }

}
