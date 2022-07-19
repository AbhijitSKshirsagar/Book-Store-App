package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.exception.BookStoreException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Cart;
import com.example.bookstoreapp.model.UserRegistration;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.CartRepository;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CartService implements ICartService {

    @Autowired
    BookRepository bookStoreRepository;

    @Autowired
    UserRegistrationRepository userRepository;

    @Autowired
    CartRepository bookStoreCartRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    @Autowired
    IUserService userService;

    @Autowired
    IBookService bookService;

    @Override
    public Cart insertItems(CartDTO cartdto) {
        Book book = bookService.getBookDataById(cartdto.getBookId());
        UserRegistration userRegistration = userService.getUserByid(cartdto.getUserId());
        int quantity = cartdto.getQuantity();
            Cart newCart = new Cart(quantity, book, userRegistration);
            return bookStoreCartRepository.save(newCart);
    }

    @Override
    public List<Cart> getCartDetails(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> cartData = bookStoreCartRepository.findById(id);
        if (cartData.isPresent()) {
            List<Cart> listOfCartdata = bookStoreCartRepository.findAll();
            log.info("ALL cart records retrieved successfully");
            return listOfCartdata;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }
    }

    @Override
    public Cart getCartDetailsById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> getCartData=bookStoreCartRepository.findById(id);
        if (getCartData.isPresent()){
            return getCartData.get();
        }
        else {
            throw new BookStoreException(" Didn't find any record for this particular cartId");
        }
    }

    @Override
    public void deleteCartItemById(String token) {
        int id = util.decodeToken(token);
        Optional<Cart> delete = bookStoreCartRepository.findById(id);
        if (delete.isPresent()) {
            bookStoreCartRepository.deleteById(id);
        } else {
            throw new BookStoreException(" Did not get any cart for specific cart id ");
        }
    }

    @Override
    public Cart updateRecordById(String token, CartDTO cartDTO) {
        // int id = util.decodeToken(token);
        // Optional<Cart> cart = bookStoreCartRepository.findById(id);
        // Optional<Book>  book = bookStoreRepository.findById(cartDTO.getBookId());
        // Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
        // if(cart.isPresent()) {
            // if(book.isPresent() && user.isPresent()) {
                // Cart cartData = new Cart(id, cartDTO.getQuantity(),book.get(), user.get());
                // bookStoreCartRepository.save(cartData);
                // log.info("Cart record updated successfully for id "+id);
                // return cartData;
            // }
            // else {
                // throw new BookStoreException("Book or User doesn't exists");
            // }
        // }
        // else {
            // throw new BookStoreException("Cart Record doesn't exists");
        //}
        return null;
    }

    public Cart getCartById(int cartId) {
        return bookStoreCartRepository.findById(cartId)
                .orElseThrow(() -> new BookStoreException("Cart with id " + cartId + " not found"));
    }

    // @Override
    // public Iterable<Cart> findAllCarts() {
        // return bookStoreCartRepository.findAll();
    // }

    @Override
    public void deleteCart(int cartId) {
        Cart cartData = this.getCartById(cartId);
        bookStoreCartRepository.delete(cartData);
    }

    @Override
    public Cart updateCartQuantity(int cartId, int quantity) {
        Cart cartData = this.getCartById(cartId);
        cartData.setQuantity(quantity);
        return bookStoreCartRepository.save(cartData);
    }

    @Override
    public Iterable<Cart> findAllCarts() {
        return bookStoreCartRepository.findAll();
    }

    @Autowired
    public List<Cart>getCartData(){
        return bookStoreCartRepository.findAll();
    }

    @Override
    public String insertItem(CartDTO cartdto) {
        Optional<Book> book = bookStoreRepository.findById(cartdto.getBookId());
        Optional<UserRegistration> userRegistration = userRepository.findById(cartdto.getUserId());
        if (book.isPresent() && userRegistration.isPresent()) {
            Cart newCart = new Cart(cartdto.getQuantity(), book.get(), userRegistration.get());
            bookStoreCartRepository.save(newCart);
            String token = util.createToken(newCart.getCartId());
            return token;
        } else {
            throw new BookStoreException("Book or User does not exists");
        }
    }

    @Override
    public Cart updateQuantity(int cartId, int quantity) {
        Cart cartData = this.getCartById(cartId);
        cartData.setQuantity(quantity);
        return bookStoreCartRepository.save(cartData);
    }

    @Override
    public Cart Update(int id ,CartDTO cartDTO){
        Optional<Cart> cart = bookStoreCartRepository.findById(id);
        Optional<Book> book = bookStoreRepository.findById(cartDTO.getBookId());
        Optional<UserRegistration> user = userRepository.findById(cartDTO.getUserId());
       // Cart newCart = new Cart();
        if (cart.isPresent()) {
            if (book.isPresent() && user.isPresent()) {
               // newCart = (cartDTO.getQuantity(), book.get(), user.get());
                Cart newCart = new Cart(cartDTO.getQuantity(),book.get(), user.get());
                bookStoreCartRepository.save(newCart);
                log.info("Cart record updated successfully for id " + id);
                //System.out.println("Hello " + id);
                return newCart;
            } else {
                throw new BookStoreException("Book or User doesn't exists");
            }
        } else {
            throw new BookStoreException("Cart Record doesn't exists");
        }
    }

}
