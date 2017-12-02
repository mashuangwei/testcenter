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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;
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


    @GetMapping("/selectById")
    public User selectById(Long id) {
        return userService.selectById(id);
    }

    @PutMapping("/updateById")
    public int updateById(User user) {
        return userService.updateById(user);
    }

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

    /**
     * 随机抛出异常.
     */
    private void randomException() throws Exception {
        Exception[] exceptions = { //异常集合
                new NullPointerException(),
                new ArrayIndexOutOfBoundsException(),
                new NumberFormatException(),
                new SQLException()};
        //发生概率
        double probability = 0.75;
        if (Math.random() < probability) {
            //情况1：要么抛出异常
            throw exceptions[(int) (Math.random() * exceptions.length)];
        } else {
            //情况2：要么继续运行
        }

    }

    /**
     * 模拟用户数据访问.
     */
    @GetMapping("/exception")
    public List index() throws Exception {
        randomException();
        return Arrays.asList("正常用户数据1!", "正常用户数据2! 请按F5刷新!!");
    }
}
