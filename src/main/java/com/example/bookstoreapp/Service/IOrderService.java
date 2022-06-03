package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.model.Order;
import java.util.List;

public interface IOrderService {

    public com.example.bookstoreapp.model.Order insertOrder(OrderDTO orderdto);

    public List<Order> getAllOrderRecords();

    public Order getOrderRecord(Integer id);

    public Order updateOrderRecord(Integer id, OrderDTO dto);

    public Order deleteOrderRecord(Integer id);

    public  Order cancelOrder(Integer id);
}
