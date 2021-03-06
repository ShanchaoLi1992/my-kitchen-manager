package com.example.myKitchenManager.controller;

import com.example.myKitchenManager.entity.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import com.example.myKitchenManager.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepository usersRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody Users users) {
        if (usersRepository.findByUserName(users.getUserName()) != null) {
            return new ResponseEntity<>(HttpStatus.IM_USED);
        }
        users.setPassword(bCryptPasswordEncoder.encode(users.getPassword()));
        usersRepository.save(users);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/login")
    public void login(@RequestBody String userName, @RequestBody String password) {
    }


//    @GetMapping("/user")
//    public List<User> allUsers() {
//        return usersRepository.findAll();
//    }
//

}
