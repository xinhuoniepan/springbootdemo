package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.User;


public interface UserService {
    public User findUserById(Long id) throws Exception;
    public User findUserByName(String username) throws Exception;
    public User saveUser(User user) throws Exception;
//    public void updateUser(User user) throws Exception;
//    public void deleteUser(Long id) ;
}
