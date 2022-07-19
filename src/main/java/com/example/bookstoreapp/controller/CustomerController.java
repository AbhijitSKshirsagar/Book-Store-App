package com.example.bookstoreapp.controller;
import com.example.bookstoreapp.dto.CustomerDTO;
import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.Service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(allowedHeaders = "*", origins = "*")
@RestController
@RequestMapping("/customer")
public class CustomerController {
    
    @Autowired
    ICustomerService iCustomerService;

    @PostMapping("/add")
    public ResponseEntity<ResponseDTO> addperson(@RequestBody CustomerDTO customerDTO){
        ResponseDTO responseDTO = new ResponseDTO("Add record  Success", iCustomerService.addCustomerDetails(customerDTO));
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id){
        ResponseDTO responseDTO = new ResponseDTO("Getting all the record..", iCustomerService.getUserById(id));
        return new ResponseEntity<ResponseDTO>(responseDTO,HttpStatus.OK);
    }
}
