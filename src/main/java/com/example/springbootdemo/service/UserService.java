package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface UserService {
    public User findUserById(Long id) throws Exception;
    public User findUserByName(String username) throws Exception;
    public Page<User> findAll(Pageable pageable);
    public void saveUser(User user) throws Exception;
//    public void updateUser(User user) throws Exception;
//    public void deleteUser(Long id) ;
}
