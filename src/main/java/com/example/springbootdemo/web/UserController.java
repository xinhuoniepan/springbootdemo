package com.example.springbootdemo.web;

import com.example.springbootdemo.dao.UserRepository;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.util.NeoProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private NeoProperties neoProperties;

    @RequestMapping("/getuser")
    @Cacheable(value = "user-key")
    public User getUser(){
        User user = userRepository.findByUsername("aa");
        return user;
    }
    @RequestMapping("/config")
    public String getConfig(){
        StringBuffer resultStr = new StringBuffer();
        String title = neoProperties.getTitle();
        String description = neoProperties.getDescription();
        resultStr.append(title).append(",").append(description).append("!");
        return resultStr.toString();
    }

    /*
    * 分业查询数据
    */
    @SuppressWarnings("deprecation")
    @RequestMapping("/list")
    public List<User> getUserList(){
        int pageNumber = 0;
        int pageSize = 5;
        //创建时间降序排序
        Pageable pageable = PageRequest.of(pageNumber,pageSize,Sort.Direction.ASC,"age");
        Page<User> pages = userRepository.findAll(pageable);
        List<User> list = pages.getContent();
        return list;
    }

}
