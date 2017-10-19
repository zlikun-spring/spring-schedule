package com.zlikun.spring.configure;

import com.zlikun.spring.job.SimpleJob;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import javax.sql.DataSource;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 14:12
 */
@Configuration
@EnableScheduling
public class QuartzConfigure {

//    /**
//     * 配置任务实例
//     * @return
//     */
//    @Bean
//    public MethodInvokingJobDetailFactoryBean jobDetailFactory() {
//        MethodInvokingJobDetailFactoryBean factory = new MethodInvokingJobDetailFactoryBean() ;
//        factory.setName("simple-job");
//        // 禁用并发，表示等上一任务执行完成才执行下一个任务
//        factory.setConcurrent(false);
//        factory.setTargetObject(new SimpleJob());
//        factory.setTargetMethod("execute");
//        factory.setArguments("zlikun" ,120);
//        return factory ;
//    }

    @Bean
    public JobDetailFactoryBean jobDetailFactory() {
        JobDetailFactoryBean factory = new JobDetailFactoryBean() ;
        // 配置作业名称
        factory.setName("simple");
        // 配置持久化
        factory.setDurability(true);
        factory.setJobClass(SimpleJob.class);
        return factory ;
    }

    /**
     * 配置触发器，这里使用cron触发器
     * @param jobDetail
     * @return
     */
    @Bean
    public CronTriggerFactoryBean cronTriggerFactory(JobDetail jobDetail) {
        CronTriggerFactoryBean factory = new CronTriggerFactoryBean() ;
        factory.setJobDetail(jobDetail);
        factory.setCronExpression("0/3 * * * * ?");
        factory.setStartDelay(3000);
        return factory ;
    }

    @Autowired
    DataSource dataSource ;

    @Bean
    public SchedulerFactoryBean schedulerFactory(ThreadPoolTaskExecutor executor ,CronTrigger cronTrigger) {
        SchedulerFactoryBean factory = new SchedulerFactoryBean() ;
        factory.setTriggers(cronTrigger);
        // 受SchedulerFactoryBean类239行代码影响，默认线程池大小为10
        // 通过修改TaskExecutor调整线程池大小(也可能通过修改quartz.properties配置文件实现)，该项配置非必要
        factory.setTaskExecutor(executor);
        // 配置数据源
        factory.setDataSource(dataSource);
        return factory ;
    }

    @Bean
    public ThreadPoolTaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor() ;
        taskExecutor.setQueueCapacity(8);
        taskExecutor.setMaxPoolSize(5);
        taskExecutor.setCorePoolSize(3);
        return taskExecutor ;
    }

}
