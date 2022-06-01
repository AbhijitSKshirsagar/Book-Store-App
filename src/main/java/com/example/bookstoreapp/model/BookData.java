//package com.example.bookstoreapp.model;
//
//import com.example.bookstoreapp.dto.BookDTO;
//import lombok.Data;
//
//import javax.persistence.*;
//
//@Entity
//@Table(name = "user_registration")
//@Data
//public class BookData {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    @Column(name = "book_store_id")
//    private String bookName;
//    private String autherName;
//    private String bookDesciption;
//    private String bookImg;
//    private String price;
//    private String quantity;
//
//    public BookData(){
//
//    }
//    public BookData(BookDTO bookDTO){
//        this.updateBookStoreData(bookDTO);
//    }
//
//    public updateBookStoreData(BookDTO bookDTO){
//        this.bookName = bookDTO.getBookName();
//        this.autherName = bookDTO.getAutherName();
//        this.bookImg = bookDTO.getBookImg();
//        this.price = bookDTO.getPrice();
//        this.quantity = bookDTO.getQuantity();
//    }
//}
