package com.example.bookstoreapp.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@Table(name="orderDetails")
public class Order {

    @Id
    @GeneratedValue
    public Integer orderID;
    public LocalDate date = LocalDate.now();
    public Integer price;
    public Integer quantity;
    public String address;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="userId")
    public UserRegistration user;
    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="bookId")
    public Book book;
    public boolean cancel;

    public Order() {
    }

    public Order(Integer orderID,Integer price, Integer quantity, String address, Book book, UserRegistration user, boolean cancel,String email) {
        this.orderID = orderID;
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

    public Order(Integer price, Integer quantity, String address, Book book, UserRegistration user, boolean cancel) {
        this.price=price;
        this.quantity=quantity;
        this.address=address;
        this.book = book;
        this.user=user;
        this.cancel = cancel;
    }

}
