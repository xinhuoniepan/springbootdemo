package com.example.springbootdemo.service;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.util.PageRequest;
import com.example.springbootdemo.util.PageResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface UserService {
    public User findUserById(Long id) throws Exception;
    public User findUserByName(String username) throws Exception;
    public List<User> selectAll();
    PageResult selectPage(PageRequest pageRequest);
    public void saveUser(User user) throws Exception;
//    public void updateUser(User user) throws Exception;
//    public void deleteUser(Long id) ;
}
