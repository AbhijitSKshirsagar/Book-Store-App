package com.example.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class UserLoginDTO {
    @Email
    public String email;
    @NotEmpty(message = "Password cant be null")
    public String password;
    public UserLoginDTO(String email,String password){
        this.email=email;
        this.password=password;
    }
}
