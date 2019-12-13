package com.example.springbootdemo.dao;

import com.example.springbootdemo.bean.User;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;


@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
    User findByUsernameOrEmail(String username,String email);
    Page<User> findAll(Pageable pageable);

    @Modifying
    @Query(value = "update user set age = :age where id = :id",nativeQuery = true)
    void updateNameById(@Param("id") Long id, @Param("age") int age);
}
