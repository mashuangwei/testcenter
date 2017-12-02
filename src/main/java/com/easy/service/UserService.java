package com.easy.service;

import com.easy.entity.User;
import com.easy.mapper.UserMapper;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public Page<User> getAll() {
        return userMapper.getAll();
    }

    public Page<User> findByPage(int pageNo, int pageSize) {
        PageHelper.startPage(pageNo, pageSize);
        return userMapper.findByPage();
    }

    @Cacheable(value = "usercache",key = "'userinfo' + #id.toString()")
    public User selectById(Long id){
        return userMapper.selectByPrimaryKey(id);
    }

    @CachePut(value = "usercache",key = "'userinfo' + #user.id.toString()")
    public int updateById(User user){
        return userMapper.updateByPrimaryKey(user);
    }


    public void add(User user){
        userMapper.insertSelective(user);
    }
}
