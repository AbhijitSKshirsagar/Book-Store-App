package com.example.bookstoreapp.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CartDTO {
    public int userId;
    public int bookId;
   // @NotNull(message="Book quantity yet to be provided")
   public int quantity;
}
