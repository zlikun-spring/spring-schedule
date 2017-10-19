package com.zlikun.spring.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 13:43
 */
@Slf4j
@Service
public class HelloService {

    @Async
    public void hello() {
        log.info("不包含返回值调用");
    }

    public Future<String> get() {
        log.info("包含返回值调用");
        return new AsyncResult<String>("Hello !") ;
    }

//    /**
//     * 后续有使用时再深入研究
//     */
//    @Async @Transactional
//    public void update() {
//        log.info("包含事务调用");
//    }

}
