package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.dto.UserLoginDTO;
import com.example.bookstoreapp.model.UserRegistration;

import java.util.List;

public interface IUserService {

    String verifyUser(String token);

    String addUser(UserDTO userDTO);

    List<UserRegistration> getAllUsers();

    ResponseDTO loginUser(UserLoginDTO userLoginDTO);

    Object getUserByToken(String token);

    String forgotPassword(String email, String password);

    UserRegistration updateUser(String id, UserDTO userDTO);

    Object getUserByEmailId(String emailId);

    List<UserRegistration> getAllUserDataByToken(String token);

    UserRegistration updateRecordById(Integer id, UserDTO userDTO);

}