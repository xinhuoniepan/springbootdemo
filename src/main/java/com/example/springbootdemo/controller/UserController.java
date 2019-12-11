package com.example.springbootdemo.controller;

import com.example.springbootdemo.dao.UserRepository;
import com.example.springbootdemo.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @RequestMapping("/getuser")
    @Cacheable(value = "user-key")
    public User getUser(){
        User user = userRepository.findByUsername("aa");
        return user;
    }

}
