package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.Service.IBookService;
import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 *  1) @RestController :-
 *           @RestController is used for making restful web services with the help of the @RestController annotation.
 *           This annotation is used at the class level and allows the class to handle the requests made by the client
 * 2) @RequestMapping :-
 *           @RequestMapping used to map web requests onto specific handler classes and/or handler methods.
 *           RequestMapping can be applied to the controller class as well as methods
 *
 * - Created controller so that we can perform rest api calls
 */
@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/book")
public class BookController {

    /**
     * 3) @AutoMapping :-
     *          @Autowiring feature of spring framework enables you to inject the object dependency implicitly.
     *          It internally uses setter or constructor injection.
     *
     * - Autowired IBookService interface so we can inject its dependency here
     */
    @Autowired
    IBookService bookService;


    @PostMapping("/insert")
    public ResponseEntity<String> addBookToRepository(@Valid @RequestBody BookDTO bookDTO){
        String newBook= bookService.createBook(bookDTO);
        ResponseDTO responseDTO=new ResponseDTO("New Book Added in Book Store",newBook);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping(value = "/getAll/{tokan}")
    public ResponseEntity<String> getAllBookData(@PathVariable String token) {
        List<Book> listOfBooks = bookService.getAllBookData(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping(value = "/getById/{token}")
    public ResponseEntity<String> getBookDataById(@PathVariable String token) {
        Book Book = bookService.getBookDataById(token);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully by id (:",Book);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<String> deleteRecordById(@PathVariable String token){
        ResponseDTO dto = new ResponseDTO("Book Record deleted successfully", bookService.deleteRecordById(token));
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @PutMapping("/updateBookById/{token}")
    public ResponseEntity<String> updateRecordById(@PathVariable String token,@Valid @RequestBody BookDTO bookDTO){
        Book updateRecord = bookService.updateRecordById(token,bookDTO);
        ResponseDTO dto = new ResponseDTO(" Book Record updated successfully by Id",updateRecord);
        return new ResponseEntity(dto,HttpStatus.ACCEPTED);
    }


    @GetMapping("searchByBookName/{name}")
    public ResponseEntity<ResponseDTO> getBookByName(@PathVariable("name") String name)
    {
        List<Book> listOfBooks = bookService.getBookByName(name);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/sortAsc")
    public ResponseEntity<ResponseDTO> getBooksInAscendingOrder()
    {
        List<Book> listOfBooks = bookService.sortedListOfBooksInAscendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/sortDesc")
    public ResponseEntity<ResponseDTO> getBooksInDescendingOrder()
    {
        List<Book> listOfBooks =bookService.sortedListOfBooksInDescendingOrder();
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("searchByAuthorName/{authorName}")
    public ResponseEntity<ResponseDTO> getBookByAuthorName(@PathVariable("authorName") String authorName)
    {
        List<Book> listOfBooks = bookService.getBookByAuthorName(authorName);
        ResponseDTO dto = new ResponseDTO("Data retrieved successfully (:",listOfBooks);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/GetAll")
    public ResponseEntity<ResponseDTO> getBookData() {
        List<Book> BookList = null ;
        BookList = bookService.getBookData();
        ResponseDTO respDTO = new ResponseDTO("Get Call Successful",BookList);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> createBookData(@Valid @RequestBody BookDTO bookDTO) {
        Book Data = null;
        Data = bookService.createBookData(bookDTO);
        ResponseDTO respDTO = new ResponseDTO("Created Employee Payroll Data Successful",Data);
        return new ResponseEntity<ResponseDTO>(respDTO,HttpStatus.OK);
    }

    @DeleteMapping("/Delete/{Id}")
    public ResponseEntity<ResponseDTO> deleteEmployeePayrollData(@PathVariable("Id") int Id) {
        bookService.deleteBookData(Id);
        ResponseDTO respDTO = new ResponseDTO("Deleted Successfully ","Deleted id : " +Id);
        return new ResponseEntity<ResponseDTO>(respDTO ,HttpStatus.OK);
    }

    @GetMapping("/search/{Id}")
    public ResponseEntity<ResponseDTO> getBookData(@PathVariable("Id")int Id) {
        Book Data=null;
        Data =  bookService.getBookDataById(Id);
        ResponseDTO respDTO = new ResponseDTO("Get Call For ID Successful",Data);
        return new ResponseEntity<ResponseDTO>(respDTO, HttpStatus.OK);
    }

}
