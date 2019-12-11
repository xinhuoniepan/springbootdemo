package com.example.springbootdemo.dao;

import com.example.springbootdemo.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameOrEmail(String username,String email);
    Page<User> findAll(Pageable pageable);
}
