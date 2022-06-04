package com.example.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class OrderDTO {

    private int quantity;
    @NotEmpty(message="Please provide address")
    private String address;
    private Integer userId;
    private Integer bookId;
    private boolean cancel;
    private int price;

}
