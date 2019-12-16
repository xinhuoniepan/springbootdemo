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
    public User findByUsername(String username);

    public User saveUser(User user);
//    @Query(value = "update user set age = :")
//    public User updateUser(User user);

////    @Query(value = "delete from user where id = :id",nativeQuery = true)
//    public void deleteUser(Long id);

    public User findByUsernameOrEmail(String username,String email);
    public Page<User> findAll(Pageable pageable);

    public void updateNameById(@Param("id") Long id, @Param("age") int age);
}
