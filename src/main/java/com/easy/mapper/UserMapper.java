package com.easy.mapper;

import com.easy.entity.User;
import com.github.pagehelper.Page;


public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
    Page<User> getAll();
    Page<User> findByPage();

}