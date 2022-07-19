package com.example.bookstoreapp.dto;


import lombok.Data;

import java.util.List;

@Data
public class CustomerDTO {
    
    String name;
    String contact;
    String address;
    String pinCode;
    String locality;
    String city;
    String landmark;
   // private String type;
}
