package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.UserMapper;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户逻辑： 如果缓存存在，从缓存中获取用户信息 如果缓存不存在，从 DB 中获取用户信息，然后插入缓存
     */
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
            user = userMapper.selectByPrimaryKey(id);
            if (null != user){
                redisUtil.set(key,JsonUtils.object2Json(user));
            }
        }
        return user;
    }

    @Override
    public User findUserByName(String username) {
        return userMapper.findUserByName(username);
    }

    /*
    * 查询用户全部信息
    */
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    /*
    * 分页查询用户信息
    */
    @Override
    public PageResult selectPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    /*
     * 分页查询用户信息
     */
    @Override
    public void saveUser(User user) {
        if(user != null){
            userMapper.insert(user);        }
    }

    /**
     * 更新用户逻辑： 先对数据库进行操作，修改完成后对缓存进行更新
     */
//    @Override
//    public void updateUser(User user) throws Exception {
//        //判断前端传来的参数以及id是否为空
//        if(user!=null){
//            Optional<User> optionalUser = userDao.findById(user.getId());
//            //判断数据库中是否有这样的数据
//            if(optionalUser != null){
//                userDao.updateUser(user);
//            } else {
//                throw new UserException("数据库中没有这条数据!!!");
//            }
//        } else {
//            throw new UserException("user为空!!!");
//        }
//        String key = RedisConfig.USER_LEY + user.getId();
//        ValueOperations<String,String> redisUtil = redisTemplate.opsForValue();
//        Boolean flag = redisTemplate.hasKey(key);
//        //缓存存在,进行更新，缓存不存在，不进行添加
//        if(flag){
//            Optional<User> optionalUser = userDao.findById(user.getId());
//            if (null != optionalUser){
//                user = optionalUser.get();
//                redisUtil.set(key,JsonUtils.object2Json(user));
//            }
//        }

//    }

    /**
     * 删除用户逻辑： 如果缓存存在，删除 如果缓存不存在，不操作
     */
//    @Override
//    public void deleteUser(Long id) {
//        userDao.deleteUser(id);
//        String key = RedisConfig.USER_LEY + id;
//        Boolean flag = redisTemplate.hasKey(key);
//        if (flag){
//            redisTemplate.delete(key);
//            logger.info("UserServiceImpl.deleteUser():从缓存中删除用户 ： id>>" + id );
//        }
//    }


    /**
     * 调用分页插件完成分页
     * @param pageRequest
     * @return
     */
    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<User> sysMenus = userMapper.selectPage();
        return new PageInfo<User>(sysMenus);
    }
}
