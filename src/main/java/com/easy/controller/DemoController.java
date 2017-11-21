package com.easy.controller;

import com.easy.entity.User;
import com.easy.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DemoController {

    //    获取properties文件参数值有两种方法，一种获得Environment 的对象，第二种就是@Value注解

    @Value("${name}")
    String name;
    @Value("${info.address}")
    private String address;


    @Autowired
    Environment environment;

    @Autowired
    private UserService userService;

    @GetMapping("/query")
    public void test() {
        System.err.println("name: " + name + " address: " + address);
        System.err.println("email.name: " + environment.getProperty("email.name"));
        System.err.println("email.address: " + environment.getProperty("email.address"));
    }

    @GetMapping("getAll")
    public PageInfo<User> getAll() {
        PageHelper.startPage(1, 4);
        Page<User> users = userService.getAll();
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }

    @GetMapping("selectAll")
    public PageInfo<User> selectAll() {
        Page<User> users = userService.findByPage(1, 3);
        PageInfo<User> userPageInfo = new PageInfo<>(users);
        return userPageInfo;
    }
}
