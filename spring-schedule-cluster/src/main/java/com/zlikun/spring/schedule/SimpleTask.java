package com.zlikun.spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 9:50
 */
@Slf4j
@Component
public class SimpleTask {

    @Scheduled(cron = "0/3 * * * * ?")
    public void execute() {
        log.info("execute task !");
    }

}
