package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.OrderDTO;
import com.example.bookstoreapp.exception.BookStoreException;
import com.example.bookstoreapp.model.Book;
import com.example.bookstoreapp.model.Order;
import com.example.bookstoreapp.model.UserRegistration;
import com.example.bookstoreapp.repository.BookRepository;
import com.example.bookstoreapp.repository.OrderRepository;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class OrderService implements IOrderService {

    @Autowired
    private OrderRepository orderRepo;

    @Autowired
    private BookRepository bookRepo;

    @Autowired
    private UserRegistrationRepository userRepo;
    @Autowired
    EmailSenderService mailService;
    @Autowired
    TokenUtility util;

    @Override
    public String insertOrder(OrderDTO orderdto) {
        Optional<Book> book = bookRepo.findById(orderdto.getBookId());
        Optional<UserRegistration> user = userRepo.findById(orderdto.getUserId());
        if (book.isPresent() && user.isPresent()) {
            if (orderdto.getQuantity() <= book.get().getQuantity()) {
                int quantity = book.get().getQuantity() - orderdto.getQuantity();
                book.get().setQuantity(quantity);
                bookRepo.save(book.get());
                int totalPrice = book.get().getPrice() * orderdto.getQuantity();
                Order newOrder = new Order(totalPrice, orderdto.getQuantity(), orderdto.getAddress(), book.get(),
                        user.get(), orderdto.isCancel());
                orderRepo.save(newOrder);
                log.info("Order record inserted successfully");
                String token = util.createToken(newOrder.getOrderID());
                mailService.sendEmail(newOrder.getUser().getEmail(), "Test Email", "Registered SuccessFully, hii: "
                        + newOrder.getOrderID() + "Please Click here to get data-> "
                        + "http://localhost:8088/order/getAllOrder/" + token);
                log.info("Order record inserted successfully");
                return token;
            } else {
                throw new BookStoreException("Requested quantity is out of stock");
            }
        } else {
            throw new BookStoreException("Book or User doesn't exists");
        }
    }

    @Override
    public List<Order> getOrderRecord(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            List<Order> listOrder = orderRepo.findAll();
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + order.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8088/order/retrieve/" + token);
            return listOrder;

        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }

    @Override
    public List<Order> getAllOrderRecords(String token) {
        Integer id = util.decodeToken(token);
        Optional<Order> orderData = orderRepo.findById(id);
        if (orderData.isPresent()) {
            List<Order> listOrderData = orderRepo.findAll();
            log.info("ALL order records retrieved successfully");
            mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get your data with this token, hii: "
                    + orderData.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8088/order/getAllOrder/" + token);
            return listOrderData;
        } else {
            System.out.println("Exception ...Token not found!");
            return null;
        }

    }

    @Override
    public Order cancelOrder(String token, int userId) {
        Integer id = util.decodeToken(token);
        Optional<Order> order = orderRepo.findById(id);
        Optional<UserRegistration> user = userRepo.findById(userId);
        if (order.isPresent() && user.isPresent()) {
            order.get().setCancel(true);
            orderRepo.save(order.get());
            mailService.sendEmail(order.get().getUser().getEmail(), "Test Email", "canceled order SuccessFully, hii: "
                    + order.get().getOrderID());
            return order.get();
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }

    @Override
    public List<Order> getAllOrderRecord() {
        List<Order> listOrder = orderRepo.findAll();
        log.info("Order record retrieved successfully for id " );
        // mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get your data with this token, hii: "          
                // + order.get().getUser().getEmail() + "Please Click here to get all data-> "
                // + "http://localhost:8088/order/retrieve/" );
        return listOrder;
    }

    @Override
    public List<Order> getOrderRecords(int id) {
        Optional<Order> order = orderRepo.findById(id);
        if (order.isPresent()) {
            List<Order> listOrder = orderRepo.findAll();
            log.info("Order record retrieved successfully for id " + id);
            mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get  your data with this token, hii: "
                    + order.get().getUser().getEmail() + "Please Click here to get all data-> "
                    + "http://localhost:8088/order/retrieve/" + id);
            return listOrder;
        } else {
            throw new BookStoreException("Order Record doesn't exists");
        }
    }
}


