package com.example.springbootdemo.bean;

import java.io.Serializable;

public class User implements Serializable {
    private static final long serialVersionUID = 950283766379694007L;

    private Long id;
    private String username;
    private String password;
    private String email;
    private String nickName;
    private String regTime;
    private Integer age;

    public User(String username, String password, String email, String nickName, String regTime, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.nickName = nickName;
        this.regTime = regTime;
        this.age = age;
    }

    public User() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", nickName='" + nickName + '\'' +
                ", regTime='" + regTime + '\'' +
                ", age=" + age +
                '}';
    }
}
