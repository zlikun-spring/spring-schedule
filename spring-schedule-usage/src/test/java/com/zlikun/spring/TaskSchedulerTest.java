package com.zlikun.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TaskScheduler 测试用例，通过类继承关系可知：ThreadPoolTaskScheduler与ThreadPoolTaskExecutor几乎相同，但多继承了TaskScheduler接口
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:11
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskSchedulerTest {

    @Configuration
    public static class Configure {

        @Bean
        public ThreadPoolTaskScheduler executor() {
            ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler();
            scheduler.setPoolSize(3);
            return scheduler ;
        }

    }

    @Autowired
    TaskScheduler scheduler ;

    @Test
    public void schedule() throws InterruptedException {

        scheduler.scheduleAtFixedRate(() -> {
            log.info("固定周期调度");
        } ,3000L) ;

        scheduler.scheduleWithFixedDelay(() -> {
            log.info("固定延时调度");
        } ,3000L) ;

        scheduler.schedule(() -> {
            log.info("使用Cron调度");
        } ,new CronTrigger("0/3 * * * * ?")) ;

        // 程序等待15秒，观察任务调度情况
        Thread.currentThread().join(15000L);

    }

}
