package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.User;
import org.apache.ibatis.annotations.*;


public interface UserDao {
    void saveUser(User user);
}
