package com.example.bookstoreapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

@SpringBootApplication
public class BookStoreAppApplication {

    public static void main(String[] args) {
        ApplicationContext context= SpringApplication.run(BookStoreAppApplication.class, args);
        System.out.println("Welcome to BookStore App....");

    }

}
