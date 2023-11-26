package com.example.restapimongojwt.models;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class User {

    @Id
    private Integer Id;

    private String email;
    private String password;

}
