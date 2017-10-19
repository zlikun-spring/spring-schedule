package com.zlikun.spring.helper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 19:27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    StringRedisTemplate template ;

    @Test
    public void test() {

        template.opsForValue().set("zlikun:simple" ,"+OK");

    }

}
