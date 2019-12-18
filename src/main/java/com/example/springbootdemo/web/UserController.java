package com.example.springbootdemo.web;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.NeoProperties;
import com.example.springbootdemo.util.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private NeoProperties neoProperties;

    @Autowired
    private UserService userService;

    @RequestMapping("/selectUserByName")
    @Cacheable(value = "user-key")
    public User getUser() throws Exception{
        User user = userService.findUserByName("aa");
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
    @RequestMapping(value = "/selectPage",method = RequestMethod.POST)
    public Object getUserList(@RequestBody PageRequest pageRequest){
        return userService.selectPage(pageRequest);
    }

    /*
     * 查询全部用户
     */
    @RequestMapping(value = "/selectAll")
    public Object getUserList(){
        return userService.selectAll();
    }

    /*
     * 通过用户名来查询用户
     */
    @RequestMapping(value = "/api/findinfo/{id}")
    public User findUserById(@PathVariable("id") Long id) throws Exception{
        return userService.findUserById(id);
    }

    /*
    * 添加用户
    */
    @RequestMapping(value = "/save")
    public void saveUser() throws Exception{
        User user = new User(1L,26, "test1@test","啊哇","test",null,"bb");
        userService.saveUser(user);
    }

}
