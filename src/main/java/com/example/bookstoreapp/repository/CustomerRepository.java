package com.example.bookstoreapp.repository;

import com.example.bookstoreapp.model.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface CustomerRepository extends JpaRepository<CustomerDetails, Integer>{
    
}
