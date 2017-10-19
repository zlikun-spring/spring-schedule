package com.zlikun.spring;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * TaskExecutor 测试用例
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 12:09
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TaskExecutorTest {

    @Configuration
    public static class Configure {

        @Bean
        public ThreadPoolTaskExecutor executor() {
            ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
            executor.setCorePoolSize(3);
            executor.setMaxPoolSize(5);
            executor.setQueueCapacity(16);
            return executor ;
        }

    }

    @Autowired
    TaskExecutor executor ;

    @Test
    public void task() {

        for (int i = 0; i < 20; i++) {
            final int index = i ;
            executor.execute(() -> {
                log.info("Hello - {}" ,index);
            });
        }

    }

}
