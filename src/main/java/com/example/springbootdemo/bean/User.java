package com.example.springbootdemo.bean;

public class User {
    private Long id;

    private Integer age;

    private String email;

    private String nickName;

    private String password;

    private String regTime;

    private String username;

    public User(Long id,Integer age, String email, String nickName, String password, String regTime, String username) {
        this.id = id;
        this.age = age;
        this.email = email;
        this.nickName = nickName;
        this.password = password;
        this.regTime = regTime;
        this.username = username;
    }

    public User() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getRegTime() {
        return regTime;
    }

    public void setRegTime(String regTime) {
        this.regTime = regTime == null ? null : regTime.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }
}