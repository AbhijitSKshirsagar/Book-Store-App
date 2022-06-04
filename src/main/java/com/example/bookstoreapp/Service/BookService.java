package com.example.bookstoreapp.Service;


import com.example.bookstoreapp.dto.BookDTO;
import com.example.bookstoreapp.exception.BookStoreException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService{

    @Autowired
    BookRepository bookStoreRepository;

    @Autowired
    TokenUtility utility;

    @Override
    public String createBook(BookDTO bookDTO) {
        Book newBook = new Book(bookDTO);
        bookStoreRepository.save(newBook);
        String token = utility.createToken(newBook.getBookId());
        return token;
    }

    @Override
    public Book getBookDataById(String token) {
        int id = utility.decodeToken(token);
        Optional<Book> getBook=bookStoreRepository.findById(id);
        if(getBook.isPresent()){
            return getBook.get();

        }
        throw new BookStoreException("Exception with id" + id + "does not exist!!");

    }

    @Override
    public List<Book> getAllBookData(String token) {
        int id = utility.decodeToken(token);
        Optional<Book> bookData = bookStoreRepository.findById(id);
        if (bookData.isPresent()) {
            List<Book> listOfBooks = bookStoreRepository.findAll();
            return listOfBooks;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public Book updataBooksByQuantity(String token, int quantity) {
        int id = utility.decodeToken(token);
        Optional<Book> book = bookStoreRepository.findById(id);
        if (book.isPresent()) {
            Book booksData = new Book();
            booksData.setQuantity(quantity);
            bookStoreRepository.save(booksData);
            return booksData;
        } else {
            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public Book updateRecordById(String token, BookDTO bookDTO) {
    int id = utility.decodeToken(token);
        Optional<Book> updateBook = bookStoreRepository.findById(id);
        if (updateBook.isPresent()) {
            Book updateUser = new Book(id, bookDTO);
            bookStoreRepository.save(updateUser);
            return updateUser;

        } else {

            throw new BookStoreException("Bookdata record does not found");
        }
    }

    @Override
    public String deleteRecordById(String token) {
        int id = utility.decodeToken(token);
        Optional<Book> newBook = bookStoreRepository.findById(id);
        if (newBook.isPresent()) {
            bookStoreRepository.deleteById(id);

        } else {
            throw new BookStoreException("Book record does not found");
        }
        return token;
    }
    @Override
    public List<Book> getBookByName(String bookName) {
        List<Book> findBook= bookStoreRepository.findByBookName(bookName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }

    @Override
    public List<Book> sortedListOfBooksInAscendingOrder() {
        List<Book> getSortedList=  bookStoreRepository.getSortedListOfBooksInAsc();
        return getSortedList;
    }

    @Override
    public List<Book> sortedListOfBooksInDescendingOrder() {
        List<Book> getSortedListInDesc=  bookStoreRepository.getSortedListOfBooksInDesc();
        return getSortedListInDesc;
    }

    @Override
    public List<Book> getBookByAuthorName(String authorName) {
        List<Book> findBook= bookStoreRepository.findByBookAuthorName(authorName);
        if(findBook.isEmpty()){
            throw new BookStoreException(" Details for provided Book is not found");
        }
        return findBook;
    }


}
