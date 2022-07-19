package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.Cart;

import java.util.List;

public interface ICartService {
    public Cart insertItems(CartDTO cartdto) ;

    Cart getCartDetailsById(String token);

    List<Cart> getCartDetails(String token);

    Cart updateRecordById(String token, CartDTO cartDTO);


    void deleteCartItemById(String token);

    public void deleteCart(int cartId);

    public Cart updateCartQuantity(int cartId, int quantity);

    public Cart getCartById(int cartId);

    public Iterable<Cart> findAllCarts();

    public List<Cart>getCartData();

    public String insertItem(CartDTO cartdto);

    public Cart updateQuantity(int cartId, int quantity);

   // public Cart updateQuantity(CartDTO quantity);

   public Cart Update(int id ,CartDTO cartDTO);

}
