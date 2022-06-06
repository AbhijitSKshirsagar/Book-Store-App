package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.model.Book;

import java.util.List;

public interface IBookService {

    String createBook(BookDTO bookDTO);

    Book getBookDataById(String token);

    List<Book> getAllBookData(String token);

    Book updateRecordById(String token, BookDTO bookDTO);

    String deleteRecordById(String token);

    List<Book> getBookByName(String bookName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();

    List<Book> getBookByAuthorName(String authorName);

}
