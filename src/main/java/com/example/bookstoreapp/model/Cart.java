package com.example.bookstoreapp.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @Id
    @GeneratedValue
    public int cartId;
    @ManyToOne
    @JoinColumn(name = "userId")
    public UserRegistration user;
    @ManyToOne
    @JoinColumn(name = "bookId")
    public Book book;
    public int quantity;

    public Cart(int cartId, int quantity, Book book, UserRegistration user) {
        super();
        this.cartId = cartId;
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public Cart(int quantity, Book book, UserRegistration user) {
        super();
        this.quantity = quantity;
        this.book = book;
        this.user = user;
    }

    public Cart() {
        super();
    }
}
