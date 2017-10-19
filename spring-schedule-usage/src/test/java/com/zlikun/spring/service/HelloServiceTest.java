package com.zlikun.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:46
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class HelloServiceTest {

    @Autowired
    HelloService helloService ;

    @Test
    public void hello() {
        helloService.hello();
    }

    @Test
    public void get() throws InterruptedException, ExecutionException {
        Future<String> future = helloService.get() ;

        // 遍历结果
        while (true) {
            if (future.isDone()) {
                log.warn("取得结果：{}" ,future.get());
                break;
            }
            log.info("继续遍历 。。。");
            Thread.sleep(1000);
        }
    }

//    @Test
//    public void update() {
//        helloService.update();
//    }

}
