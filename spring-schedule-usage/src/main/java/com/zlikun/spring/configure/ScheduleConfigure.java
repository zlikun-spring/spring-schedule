package com.zlikun.spring.configure;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:40
 */
@Slf4j
@Configuration
@EnableAsync
@EnableScheduling
public class ScheduleConfigure {

    /**
     * 配置任务执行器
     * @return
     */
    @Bean
    public ThreadPoolTaskExecutor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(3);
        executor.setMaxPoolSize(5);
        executor.setQueueCapacity(16);
        return executor ;
    }

    /**
     * 配置任务调度器(优先级高于前者)
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler scheduler() {
        ThreadPoolTaskScheduler scheduler = new ThreadPoolTaskScheduler() ;
        scheduler.setPoolSize(5);
        scheduler.setThreadNamePrefix("scheduler-");
        scheduler.setRejectedExecutionHandler(new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                log.info("拒绝执行任务!");
            }
        });
        return scheduler ;
    }

}
