package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.Cart;

public interface ICartService {
    Cart insertItems(CartDTO cartdto);
    Cart getCartDetailsById(Integer cartId);

    Cart deleteCartItemById(Integer cartId);

    Cart updateRecordById(Integer cartId, CartDTO cartDTO);

    Cart getCartRecordByBookId(Integer bookID);

    ResponseDTO getCartDetails();
}
