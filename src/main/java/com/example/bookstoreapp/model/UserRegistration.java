package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.UserDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class UserRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int userId;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;


    public UserRegistration() {

    }

    public UserRegistration(UserRegistration userRegistration){
        this.userId = userRegistration.getUserId();
        this.firstName = userRegistration.getFirstName();
        this.lastName = userRegistration.getLastName();
        this.address = userRegistration.getAddress();
        this.email = userRegistration.getEmail();
        this.password= userRegistration.getPassword();
    }
    public UserRegistration(UserDTO userDTO){
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.address = userDTO.getAddress();
        this.email = userDTO.getEmail();
        this.password= userDTO.getPassword();
    }
    public UserRegistration(int userId, UserDTO userDTO){
        this.userId=userId;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address= userDTO.getAddress();
        this.email= userDTO.getEmail();
        this.password= userDTO.getPassword();
    }

    public UserRegistration(String email_id, UserDTO userDTO) {
        this.email=email_id;
        this.firstName= userDTO.getFirstName();
        this.lastName= userDTO.getLastName();
        this.address=userDTO.getAddress();
        this.password= userDTO.getPassword();
    }
    public void UpdateUser(UserDTO userDTO) {
        this.firstName= userDTO.getFirstName();
        this.lastName=userDTO.getLastName();
        this.email= userDTO.getEmail();
        this.address=userDTO.getAddress();
        this.password=userDTO.getPassword();
    }
}
