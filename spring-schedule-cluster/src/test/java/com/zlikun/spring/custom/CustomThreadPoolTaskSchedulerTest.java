package com.zlikun.spring.custom;

import com.zlikun.spring.configure.ScheduleConfigure;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 20:39
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(classes = { ScheduleConfigure.class })
public class CustomThreadPoolTaskSchedulerTest {

    @Autowired
    CustomThreadPoolTaskScheduler scheduler ;

    @Test
    public void schedule() throws InterruptedException {

        // 此处只是将任务加入到调度中，但执行调度的逻辑在哪里？
        // 找到执行调度的地方，实现集群控制！！！
        scheduler.schedule(() -> {
            log.info("执行调度!");
        } ,new CronTrigger("0/15 * * * * ?")) ;

        Thread.currentThread().join(60000);

    }

}
