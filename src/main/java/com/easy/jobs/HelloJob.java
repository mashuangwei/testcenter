package com.easy.jobs;

/**
 * 作者: mashuangwei
 * 日期: 2017/11/7
 */

import com.easy.entity.User;
import com.easy.service.UserService;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class HelloJob implements BaseJob {

    private static Logger _log = LoggerFactory.getLogger(HelloJob.class);

    @Autowired
    private UserService userService;

    public HelloJob() {

    }

    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        _log.error("Hello Job执行时间: " + new Date());
        User user = new User();
        user.setName("aa");
        user.setAge(22);
        userService.add(user);

    }
}