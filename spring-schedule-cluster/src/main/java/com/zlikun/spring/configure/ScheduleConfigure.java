package com.zlikun.spring.configure;

import com.zlikun.spring.custom.CustomThreadPoolTaskScheduler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:40
 */
@Slf4j
@Configuration
@EnableScheduling
public class ScheduleConfigure {

    @Bean
    public ThreadPoolTaskExecutor executor() {
        return new ThreadPoolTaskExecutor();
    }

    @Bean
    public CustomThreadPoolTaskScheduler scheduler() {
        // 自定义一个调度器，实现集群特性增强
        CustomThreadPoolTaskScheduler scheduler = new CustomThreadPoolTaskScheduler() ;
        scheduler.setPoolSize(5);
        return scheduler ;
    }

}
