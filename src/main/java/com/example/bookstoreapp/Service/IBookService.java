package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.model.Book;

import java.util.List;

public interface IBookService {

    Book createBook(BookDTO bookDTO);

    Book getBookDataById(int BookId);

    List<Book> getAllBookData();

    Book updateRecordById(Integer BookId, BookDTO bookDTO);

    Object deleteRecordById(int BookId);

    List<Book> getBookByName(String bookName);

    List<Book> sortedListOfBooksInAscendingOrder();

    List<Book> sortedListOfBooksInDescendingOrder();

    List<Book> getBookByAuthorName(String authorName);

}
