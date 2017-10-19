package com.zlikun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ScheduleApplication {

	/**
	 * 测试集群环境下，任务执行情况
	 * java -jar schedule.jar --server.port=8001
	 * java -jar schedule.jar --server.port=8002
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ScheduleApplication.class, args);
	}

}
