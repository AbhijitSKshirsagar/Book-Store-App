package com.example.bookstoreapp.model;

import com.example.bookstoreapp.dto.CustomerDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor

public class CustomerDetails {

    @Id
    @Column(name = "customerId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int id;
    String name;
    String contact;
    String address;
    String pinCode;
    String locality;
    String city;
    String landmark;

    public CustomerDetails(CustomerDTO customerDTO) {
        this.id=id;
        this.name = customerDTO.getName();
        this.contact = customerDTO.getContact();
        this.address = customerDTO.getAddress();
        this.pinCode = customerDTO.getPinCode();
        this.locality = customerDTO.getLocality();
        this.city = customerDTO.getCity();
        this.landmark = customerDTO.getLandmark();
    }
    
}
