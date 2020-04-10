package com.example.springbootdemo.service.impl;

import com.example.springbootdemo.bean.ResponseResult;
import com.example.springbootdemo.bean.User;
import com.example.springbootdemo.dao.UserMapper;
import com.example.springbootdemo.exception.UserException;
import com.example.springbootdemo.service.UserService;
import com.example.springbootdemo.util.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private UserMapper userMapper;

    /**
     * 获取用户逻辑： 如果缓存存在，从缓存中获取用户信息 如果缓存不存在，从 DB 中获取用户信息，然后插入缓存
     */
    @Override
    public User findUserById(Long id) {
        User user = null;
        String key = RedisConfig.USER_KEY + id;
        ValueOperations<String, String> redisUtil = redisTemplate.opsForValue();
        Boolean flag = redisTemplate.hasKey(key);
        //缓存存在
        if (flag) {
            Object object = redisUtil.get(key);
            user = JsonUtils.json2Object(object.toString(), User.class);
        } else {
            user = userMapper.selectByPrimaryKey(id);
            if (null != user) {
                redisUtil.set(key, JsonUtils.object2Json(user));
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
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    /*
     * 添加用户信息
     */
    @Override
    public ResponseResult saveUser(User user) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        if (user != null) {
            int count = 0;
            try {
                count = userMapper.insert(user);
            } catch (Exception e){
                responseResult.setSuccess(false);
                responseResult.setMessage("数据插入失败");
            }

            if(count == 0){
                throw new UserException("数据插入失败");
            }
            responseResult.setSuccess(true);
            responseResult.setMessage("数据添加成功");
        }
        return responseResult;
    }

    /**
     * 更新用户逻辑： 先对数据库进行操作，修改完成后对缓存进行更新
     */
    @Override
    public ResponseResult updateUser(User user) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        //判断前端传来的参数以及id是否为空
        if(user!=null){
            User user1 = userMapper.selectByPrimaryKey(user.getId());
            //判断数据库中是否有这样的数据
            if(user1 != null){
                userMapper.updateByPrimaryKeySelective(user);
            } else {
                throw new UserException("数据库中没有这条数据!!!");
            }
        } else {
            throw new UserException("user为空!!!");
        }
        String key = RedisConfig.USER_KEY + user.getId();
        ValueOperations<String,String> redisUtil = redisTemplate.opsForValue();
        Boolean flag = redisTemplate.hasKey(key);
        //缓存存在,进行更新，缓存不存在，不进行添加
        if(flag){
            User user1 = userMapper.selectByPrimaryKey(user.getId());
            if (null != user1){
                redisUtil.set(key,JsonUtils.object2Json(user));
            }
        }
        responseResult.setSuccess(true);
        responseResult.setMessage("数据修改成功");
        return responseResult;
    }

    /**
     * 删除用户逻辑： 如果缓存存在，删除 如果缓存不存在，不操作
     */
    @Override
    public ResponseResult deleteUser(Long id) throws Exception {
        ResponseResult responseResult = new ResponseResult();
        int count = userMapper.deleteByPrimaryKey(id);
        if (count == 0) {
            responseResult.setSuccess(false);
            responseResult.setMessage("数据删除失败");
            throw new UserException("数据库中id为" + id + "的数据不存在");
        }
        String key = RedisConfig.USER_KEY + id;
        Boolean flag = redisTemplate.hasKey(key);
        if (flag) {
            redisTemplate.delete(key);
            logger.info("UserServiceImpl.deleteUser():从缓存中删除用户 ： id>>" + id);
        }
        responseResult.setSuccess(true);
        responseResult.setMessage("数据删除成功");
        return responseResult;
    }


    /**
     * 调用分页插件完成分页
     *
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
