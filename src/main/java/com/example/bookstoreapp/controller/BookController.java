//package com.example.bookstoreapp.controller;
//
//
//import com.example.bookstoreapp.Service.BookService;
//import com.example.bookstoreapp.Service.IBookService;
//import com.example.bookstoreapp.dto.ResponseDTO;
//import com.example.bookstoreapp.model.BookData;
//import com.sun.net.httpserver.HttpServer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/book")
//public class BookController {
//
//    @Autowired
//    IBookService bookService;
//
//    @GetMapping("/getAll")
//    public ResponseEntity<ResponseDTO> GetAllBook(){
//        List<BookData> bookData = bookService.getAllbook();
//        ResponseDTO responseDTO = new ResponseDTO("Get all Books data", bookData);
//        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
//    }
//
//}
