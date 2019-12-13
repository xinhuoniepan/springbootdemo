package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.UserRepository;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.JsonUtils;
import com.example.springbootdemo.util.RedisConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserRepository userDao;

    @Override
    public User findUserById(Long id) {
        User user = null;
        String key = RedisConfig.USER_LEY + id;
        ValueOperations<String,String> redisUtil = redisTemplate.opsForValue();
        Boolean flag = redisTemplate.hasKey(key);
        //缓存存在
        if(flag){
            Object object = redisUtil.get(key);
            user = JsonUtils.json2Object(object.toString(),User.class);
        } else {
            Optional<User> optionalUser = userDao.findById(id);
            if (null != optionalUser){
                user = optionalUser.get();
                redisUtil.set(key,JsonUtils.object2Json(user));
            }
        }
        return user;
    }
}
