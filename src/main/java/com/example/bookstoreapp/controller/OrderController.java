package com.example.bookstoreapp.controller;


import com.example.bookstoreapp.Service.IOrderService;
import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bookstoreapp.model.Order;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private IOrderService orderService;


    @PostMapping("/insert")
    public ResponseEntity<ResponseDTO> insertOrder(@Valid @RequestBody OrderDTO orderdto) {
        String newOrder = orderService.insertOrder(orderdto);
        ResponseDTO dto = new ResponseDTO("Order placed successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.CREATED);
    }


    @GetMapping("/getAllOrder/{tokan}")
    public ResponseEntity<ResponseDTO> getAllOrderRecords(@PathVariable String token) {
        List<Order> newOrder = orderService.getAllOrderRecords(token);
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @GetMapping("/getbyId/{token}")
    public ResponseEntity<ResponseDTO> getBookRecord(@PathVariable String token) {
        List<Order> newOrder = orderService.getOrderRecord(token);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }


    @PutMapping("/cancelOrder/{token}/{userId}")
    public ResponseEntity<ResponseDTO> getCancelOrder(@PathVariable String token, @PathVariable int userId){
        Order deletedOrder = orderService.cancelOrder(token, userId);
        ResponseDTO dto = new ResponseDTO("Cancel order successfully !",deletedOrder);
        return new ResponseEntity(dto,HttpStatus.OK);
    }

    @GetMapping("/getAllOrder")
    public ResponseEntity<ResponseDTO> getAllOrderRecord() {
        List<Order> newOrder = orderService.getAllOrderRecord();
        ResponseDTO dto = new ResponseDTO("All records retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<ResponseDTO> getBookRecords(@PathVariable int id) {
        List<Order> newOrder = orderService.getOrderRecords(id);
        ResponseDTO dto = new ResponseDTO("Record retrieved successfully !", newOrder);
        return new ResponseEntity(dto, HttpStatus.OK);
    }

}
