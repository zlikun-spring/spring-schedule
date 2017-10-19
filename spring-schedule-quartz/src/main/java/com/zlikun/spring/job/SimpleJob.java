package com.zlikun.spring.job;

import lombok.extern.slf4j.Slf4j;

/**
 * Job示例
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 14:26
 */
@Slf4j
public class SimpleJob {

    public void execute(String name ,int age) {
        log.info("{}年龄是{}岁!" ,name ,age);
    }

}
