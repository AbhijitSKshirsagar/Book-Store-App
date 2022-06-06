package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.Cart;

import java.util.List;

public interface ICartService {
    String insertItems(CartDTO cartdto) ;

    Cart getCartDetailsById(String token);

    List<Cart> getCartDetails(String token);

    Cart updateRecordById(String token, CartDTO cartDTO);


    void deleteCartItemById(String token);
}
