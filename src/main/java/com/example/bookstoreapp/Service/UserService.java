package com.example.bookstoreapp.Service;

import com.example.bookstoreapp.dto.ResponseDTO;
import com.example.bookstoreapp.dto.UserDTO;
import com.example.bookstoreapp.dto.UserLoginDTO;
import com.example.bookstoreapp.exception.BookStoreException;
import com.example.bookstoreapp.model.UserRegistration;
import com.example.bookstoreapp.repository.UserRegistrationRepository;
import com.example.bookstoreapp.util.EmailSenderService;
import com.example.bookstoreapp.util.TokenUtility;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService implements IUserService {
    @Autowired
    private UserRegistrationRepository userRepository;

    @Autowired
    EmailSenderService mailService;

    @Autowired
    TokenUtility util;

    @Override
    public String verifyUser(String token) {
        int id = Math.toIntExact(util.decodeToken(token));
        Optional<UserRegistration> user = userRepository.findById(id);

        if (user.isPresent()) {
            return user.toString();
        } else
            return null;
    }
    @Override
    public String addUser(UserDTO userDTO) {
        UserRegistration newUser= new UserRegistration(userDTO);
        userRepository.save(newUser);
        String token = util.createToken(newUser.getUserId());
        mailService.sendEmail(newUser.getEmail(), "Test Email", "Registered SuccessFully, hii: "
                +newUser.getFirstName()+"Please Click here to get data-> "
                +"http://localhost:8088/user/verify/"+token);
        return token;
    }

    @Override
    public List<UserRegistration> getAllUsers() {
        List<UserRegistration> getUsers= userRepository.findAll();
        return getUsers;
    }
    @Override
    public Object getUserByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> getUser= userRepository.findById(id);
        if(getUser.isPresent()){
            mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +getUser.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8088/user/getAll/"+token);
            return getUser;

        }
        else {
            throw new BookStoreException("Record for provided userId is not found");
        }
    }

    @Override
    public Optional<UserRegistration> userLogin(UserLoginDTO userLoginDTO) {
       // ResponseDTO dto = new ResponseDTO();
        Optional<UserRegistration> login = userRepository.findByEmailId(userLoginDTO.getEmail(),userLoginDTO.getPassword());
        if (login.isPresent()) {
            log.info("LOGIN SUCCESSFUL");
            return login;
       } else {
           System.out.println("User not Found Exception:");
           throw (new BookStoreException("Record not Found"));
       }
    }

    @Override
    public String forgotPassword(String email, String password) {
        Optional<UserRegistration> isUserPresent = userRepository.findByEmailid(email);

        if(!isUserPresent.isPresent()) {
            throw new BookStoreException("Book record does not found");
        }
        else {
            UserRegistration user = isUserPresent.get();
            user.setPassword(password);
            userRepository.save(user);
            return "Password updated successfully";
        }
    }

    @Override
    public Object getUserByEmailId(String emailId) {

        return userRepository.findByEmailid(emailId);
    }

    @Override
    public UserRegistration updateUser(String id, UserDTO userDTO) {
        Optional<UserRegistration> updateUser = userRepository.findByEmailid(id);
        if(updateUser.isPresent()) {
            UserRegistration newBook = new UserRegistration(updateUser.get().getUserId(),userDTO);
            userRepository.save(newBook);
            String token = util.createToken(newBook.getUserId());
            mailService.sendEmail(newBook.getEmail(),"Welcome "+newBook.getFirstName(),"Click here \n http://localhost:8088/user/update/"+token);
            return newBook;
        }
        throw new BookStoreException("Book Details for email not found");
    }
    @Override
    public List<UserRegistration> getAllUserDataByToken(String token) {
        int id=util.decodeToken(token);
        Optional<UserRegistration> isContactPresent=userRepository.findById(id);
        if(isContactPresent.isPresent()) {
            List<UserRegistration> listOfUsers=userRepository.findAll();
            mailService.sendEmail("kshirsagarabhijit360@gmail.com", "Test Email", "Get your data with this token, hii: "
                    +isContactPresent.get().getEmail()+"Please Click here to get data-> "
                    +"http://localhost:8088/user/getAll/"+token);
            return listOfUsers;
        }else {
            System.out.println("Exception ...Token not found!");
            return null;	}
    }

    @Override
    public UserRegistration updateRecordById(Integer id, UserDTO userDTO) {
        Optional<UserRegistration> addressBook = userRepository.findById(id);
        if(addressBook.isPresent()) {
            UserRegistration newBook = new UserRegistration(id,userDTO);
            userRepository.save(newBook);
            return newBook;
        }
        throw new BookStoreException("User Details for id not found");
    }
    @Override
    public UserRegistration getUserByid(int id) {
        return userRepository.findById(id).orElseThrow(() -> new BookStoreException("User  with id " + id + " does not exist in database..!"));  
    }
}
