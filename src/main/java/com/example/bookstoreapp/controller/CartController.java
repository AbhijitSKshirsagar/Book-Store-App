package com.example.bookstoreapp.controller;

import com.example.bookstoreapp.Service.ICartService;
import com.example.bookstoreapp.dto.CartDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService cartService;

    @PostMapping("/create")
    public ResponseEntity<ResponseDTO> insertItem(@Valid @RequestBody CartDTO cartdto) {
        Cart newCart = cartService.insertItems(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/getAll/{token}")
    public ResponseEntity<ResponseDTO> getAllCartDetails(@PathVariable String token) {
        List<Cart> cart = cartService.getCartDetails(token);
        ResponseDTO responseDTO = new ResponseDTO("Get call Success", cart);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getById/{token}")
    public ResponseEntity<ResponseDTO> getCartDetailsById(@PathVariable String token) {
        Cart specificCartDetail = cartService.getCartDetailsById(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart details retrieved successfully", specificCartDetail);
        return new ResponseEntity(responseDTO, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/delete/{token}")
    public ResponseEntity<ResponseDTO> deleteCartById(@PathVariable String token) {
        cartService.deleteCartItemById(token);
        ResponseDTO responseDTO = new ResponseDTO("Cart delete successfully", token);
        return new ResponseEntity(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateById/{token}")
    public ResponseEntity<ResponseDTO> updateCartById(@PathVariable String token, @Valid @RequestBody CartDTO cartDTO) {
        Cart updateRecord = cartService.updateRecordById(token, cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id", updateRecord);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }

    @GetMapping("/get_by_id/{cartId}")
    public ResponseEntity<ResponseDTO> getCartById(@PathVariable("cartId") int cartId) {
        Cart cartData = cartService.getCartById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("Get Cart call Success for Id", cartData);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/GetAllCartItems")
    public ResponseEntity<ResponseDTO> findAllCart() {
        Iterable<Cart> allCarts = cartService.findAllCarts();
        ResponseDTO responseDTO = new ResponseDTO("All Items in Carts", allCarts);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PostMapping("/creates")
    public ResponseEntity<ResponseDTO> insertItems(@Valid @RequestBody CartDTO cartdto) {
        String newCart = cartService.insertItem(cartdto);
        ResponseDTO responseDTO = new ResponseDTO("User registered successfully !", newCart);
        return new ResponseEntity(responseDTO, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete_cart/{id}")
    public ResponseEntity<ResponseDTO> deleteCart(@PathVariable int id){
        cartService.deleteCart(id);
        ResponseDTO responseDTO=new ResponseDTO("deleted succesfully",id);
        return  new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> Update(@PathVariable int id, @Valid @RequestBody CartDTO cartDTO ){
        Cart updateRecordById = cartService.Update(id,cartDTO);
        ResponseDTO dto = new ResponseDTO(" Cart Record updated successfully by Id", updateRecordById);
        return new ResponseEntity(dto, HttpStatus.ACCEPTED);
    }
     
}