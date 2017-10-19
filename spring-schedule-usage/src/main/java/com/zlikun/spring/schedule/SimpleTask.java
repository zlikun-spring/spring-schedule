package com.zlikun.spring.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @author zlikun <zlikun-dev@hotmail.com>
 * @date 2017/10/19 9:50
 */
@Slf4j
@Component
public class SimpleTask {

    /**
     * 每3秒执行一次任务
     */
//    @Scheduled(fixedRate = 3000)
    public void task_1() {
        log.info("执行任务-1");
    }

    /**
     * 每3秒执行一次任务，初次执行延迟5秒
     * 通过与任务1对比第一次执行时间，可以验证是否延迟了5秒
     *
     * 单线程场景时，程序执行耗时超过任务调度间隔时，后面的任务会以前面任务的结束时间为开始时间执行
     * 10:29:54.399 begin
     * 10:29:59.400 end (相对于开始时间间隔5秒，程序执行耗时5秒)
     * 10:29:59.400 begin (相对于上次完成时间间隔0秒，由于上次任务执行完成时，第二次任务开始时间已经过了或者刚好开始，所以立即开始了)
     * 10:30:04.401 end (相对于开始时间间隔5秒，程序执行耗时5秒)
     */
//    @Scheduled(fixedRate = 3000 ,initialDelay = 5000)
    public void task_2() throws InterruptedException {
        String uuid = UUID.randomUUID().toString() ;
        log.info("执行任务-2-begin-\t" + uuid);
        Thread.sleep(5000);
        log.info("执行任务-2-end-\t" + uuid);
    }

    /**
     * fixedRate、fixedDelay、cron都用于配置任务执行周期，三者只能同时使用一个
     * cron 使用cron表达式实现
     * fixedRate 使用固定周期执行，即：下一次任务执行并不关注上一次任务是否执行完成
     * fixedDelay 使用固定延时执行，下一次任务执行周期是相对于上一次完成时间为基准
     *
     * 本例以2秒执行一次的频率执行任务，但任务至少耗时3秒，以此来观察上次任务完成时间对于下次任务的影响
     * 10:20:53.698 begin
     * 10:20:56.698 end (开始到完成间隔3秒)
     * 10:20:58.699 begin (第二次执行相对于第一次完成间隔2秒，实际与第一次间隔5秒)
     * 10:21:01.699 end (第二次执行完成时间相对于开始时间间隔3秒)
     * @throws InterruptedException
     */
//    @Scheduled(fixedDelay = 2000)
    public void task_3() throws InterruptedException {
        String uuid = UUID.randomUUID().toString() ;
        log.info("执行任务-3-begin-\t" + uuid);
        Thread.sleep(3000);
        log.info("执行任务-3-end-\t" + uuid);
    }

    /**
     * cron 表达式
     * 1、秒 0 ~ 59
     * 2、分 0 ~ 59
     * 3、时 0 ~ 23
     * 4、天 0 ~ 30 ，取值范围与所属月分有关
     * 5、月 0 ~ 11
     * 6、周 1 ~ 7，或：SUN，MON，TUE，WED，THU，FRI，SAT
     * 7、年 1970 ~ 2099，可以省略(Spring-4.x已不支持该项了)
     * 每个部分配置方式
     * 1、单个数值，如：3
     * 2、连续区间，如：2-5
     * 3、间隔时间，如：0/3
     * 4、一个列表，如：(1,3,7)
     * 特殊符号
     * 1、* 表示表示每单位，如：每天、每分等
     * 2、? 仅用于DayofWeek和DayofMonth域，表示不指定值
     * 3、L 表示最后，只能出现在DayofWeek和DayofMonth域
     * 4、W 表示有效工作日(周一到周五),只能出现在DayofMonth域
     * 5、LW 这两个字符可以连用，表示在某个月最后一个工作日，即最后一个星期五
     * 6、# 用于确定每个月第几个星期几，只能出现在DayofMonth域，如：4#2，表示某月的第二个星期三
     * 示例
     * 1、0 15 10 * * ?          每天上午10:15触发
     * 2、0 15 10 ? * *          每天上午10:15触发
     * 3、0 15 10 * * ? *        每天上午10:15触发
     * 4、0 * 14 * * ?           每天下午2点到下午2:59期间的每1分钟触发
     * 5、0 0/30 9-17 * * ?      朝九晚五工作时间内每半小时触发
     * 6、0 0 10,14,16 * * ?     每天上午10点、下午2点、下午4点触发
     * 7、0 10,44 14 ? 3 WED     每年三月的星期三的下午2:10和2:44触发
     * 8、0 15 10 ? * MON-FRI    周一至周五的上午10:15触发
     * 9、0 15 10 L * ?          每月最后一日的上午10:15触发
     * 0、0 15 10 ? * 6L         每月的最后一个星期五上午10:15触发
     * 1、0 15 10 ? * 6L 2002-2005   2002年至2005年的每月的最后一个星期五上午10:15触发
     * 0 15 10 ? * 6#3           每月的第三个星期五上午10:15触发
     * @throws InterruptedException
     * 示例列表每3秒执行一次
     * 11:44:18.001 begin
     * 11:44:23.002 end (执行耗时5秒)
     * 11:44:24.000 begin (间隔上次任务开始时间6秒，中间有一次任务被忽略了，该任务的开始时间在上次任务执行过程中出现)
     * 11:44:29.001 end (执行耗时5秒)
     */
//    @Scheduled(cron = "0/3 * * * * ?")
    public void task_4() throws InterruptedException {
        String uuid = UUID.randomUUID().toString() ;
        log.info("执行任务-4-begin-\t" + uuid);
        Thread.sleep(5000);
        log.info("执行任务-4-end-\t" + uuid);
    }

}
