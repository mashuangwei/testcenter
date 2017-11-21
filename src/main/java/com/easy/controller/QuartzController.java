package com.easy.controller;

import com.easy.entity.User;
import com.easy.jobs.HelloJob;
import com.easy.service.UserService;
import com.easy.task.MyTask;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.Map;

@RestController
@RequestMapping("")
public class QuartzController {
    @Autowired
    UserService userService;

    @PostMapping("/add")
    public User add(User user) throws Exception {
        // 通过SchedulerFactory获取一个调度器实例
        userService.add(user);
        return user;
    }

//    @Autowired
//    private Scheduler scheduler;

    @Autowired
    private MyTask myTask;

    @PostMapping("/addjob")
    public void addJob() {
        myTask.addJob("","");
//        JobDetail jobDetail = JobBuilder.newJob(HelloJob.class).withIdentity("job").withIdentity("com.easy.jobs.HelloJob", "group").build();
//        //表达式调度构建器(即任务执行的时间)
//        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule("0/10 * * * * ?");
//
//        //按新的cronExpression表达式构建一个新的trigger
//        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity("com.easy.jobs.HelloJob", "group").withSchedule(scheduleBuilder).build();
//
//        try {
//            scheduler.scheduleJob(jobDetail, trigger);
//        } catch (SchedulerException e) {
//            System.out.println("创建定时任务失败" + e);
//            try {
//                throw new Exception("创建定时任务失败");
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }

    }

    @DeleteMapping("/delete")
    public void delete(){
        myTask.deletejob("com.easy.jobs.HelloJob", "group");
    }

    @GetMapping("/queryjob")
    public Map<String, Object> query(@RequestParam(value = "pageNum", defaultValue = "" + 1) Integer pageNum, @RequestParam(value = "pageSize",defaultValue = "" + 20) Integer pageSize) {
        return myTask.queryjob(pageNum, pageSize);
    }

    @PostMapping(value = "/reschedulejob")
    public void rescheduleJob() {
        try {
            myTask.rescheduleJob("com.easy.jobs.HelloJob", "group", "0/5 * * * * ?");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping(value = "/resumejob")
    public void resumejob(){
        myTask.resumejob("com.easy.jobs.HelloJob", "group");
    }

    @PostMapping(value = "/pausejob")
    public void pausejob()  {
        myTask.pausejob("com.easy.jobs.HelloJob", "group");
    }

}
