package com.example.springbootdemo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloController {

//    @RequestMapping(value="hello",method = RequestMethod.GET)
    @RequestMapping("hello")
    public String hello(){
        return "hello world";
    }
}
