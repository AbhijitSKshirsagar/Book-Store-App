package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.CustomerDTO;

public interface ICustomerService {
    
    public Object addCustomerDetails(CustomerDTO customerDTO);

    Object getUserById(int id);
}
