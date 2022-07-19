package com.example.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDTO {

    public int quantity;
   // @NotEmpty(message="Please provide address")
    public String address;
    public Integer userId;
    public Integer bookId;
    public boolean cancel;
    public int price;

}
