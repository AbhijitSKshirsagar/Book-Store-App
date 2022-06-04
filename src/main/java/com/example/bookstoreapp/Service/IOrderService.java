package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.model.Order;
import java.util.List;

public interface IOrderService {

    String insertOrder(OrderDTO orderdto);
    List <Order> getOrderRecord(String token);
    List<Order> getAllOrderRecords(String token);
    Order cancelOrder(String token,int userId);
}

