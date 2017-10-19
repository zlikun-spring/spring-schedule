package com.zlikun.spring.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * Job示例
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 14:26
 */
@Slf4j
public class SimpleJob extends QuartzJobBean {

    public void execute(String name ,int age) {
        log.info("{}年龄是{}岁!" ,name ,age);
    }

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        log.info("执行任务。。。");
    }
}
