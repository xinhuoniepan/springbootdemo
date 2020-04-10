package com.example.springbootdemo.web;

import com.example.springbootdemo.bean.ResponseResult;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.CommonRequestEntity;
import com.example.springbootdemo.util.JsonUtils;
import com.example.springbootdemo.util.NeoProperties;
import com.example.springbootdemo.util.PageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

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
    @RequestMapping(value = "/findinfo/{id}")
    public User findUserById(@PathVariable("id") Long id) throws Exception{
        return userService.findUserById(id);
    }

    /*
    * 添加用户
    */
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveUser(@RequestBody CommonRequestEntity user) throws Exception{
        logger.info(user.getData().toString());
        User user1 = JsonUtils.json2Object(JsonUtils.object2Json(user.getData()),User.class);
        ResponseResult result = userService.saveUser(user1);
        return result;
    }

    /*
    * 通过id删除用户
    */
    @RequestMapping(value = "/deleteById",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult deleteUsers(@RequestBody String id) throws Exception{
        if(id != null) {
            Long nid = Long.valueOf(id);
            return userService.deleteUser(nid);
        }
        return new ResponseResult(true,"失败");
     }

    /*
     * 修改用户信息
     */
    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateUser(@RequestBody User user) throws Exception{
        return userService.updateUser(user);
    }

}
