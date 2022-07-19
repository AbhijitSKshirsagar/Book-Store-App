package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CustomerDTO;
import com.example.bookstoreapp.exception.BookStoreException;
import com.example.bookstoreapp.model.CustomerDetails;
import com.example.bookstoreapp.repository.CustomerRepository;
import com.example.bookstoreapp.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class CustomerService implements ICustomerService{

    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    TokenUtility tokenUtility;

    @Override
    public Object addCustomerDetails(CustomerDTO customerDTO) {
        CustomerDetails cusomer = new CustomerDetails(customerDTO);
        return customerRepository.save(cusomer);
    }

    @Override
    public Object getUserById(int id) {
        return customerRepository.findById(id).orElseThrow(() -> new BookStoreException("Customer with id " + id + " does not exist in database..!"));

    }
    
}
