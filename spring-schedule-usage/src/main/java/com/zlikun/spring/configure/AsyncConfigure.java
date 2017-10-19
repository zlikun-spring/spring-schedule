package com.zlikun.spring.configure;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:40
 */
@Configuration
@EnableAsync
public class AsyncConfigure {

//    @Bean
//    public ThreadPoolTaskExecutor executor() {
//        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
//        executor.setCorePoolSize(3);
//        executor.setMaxPoolSize(5);
//        executor.setQueueCapacity(16);
//        return executor ;
//    }

}
