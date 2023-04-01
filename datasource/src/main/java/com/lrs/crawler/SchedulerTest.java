package com.lrs.crawler;

import java.util.Timer;
import java.util.TimerTask;

/**
 * 演示定时任务
 */
//@Component//表示将该类交给Spring管理，作为spring容器中的对象
public class SchedulerTest {
    public static void main(String[] args) {
        //演示JDK中自带的定时任务API
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("每隔1s执行一次");
            }
        },1000,1000);
    }

    //演示Springboot中提供的定时任务工具
//    @Scheduled(initialDelay = 1000,fixedDelay = 1000)
//    @Scheduled(cron="0/1 * * * * ?")//每隔一秒中执行一次
//    @Scheduled(cron="0 0 8 * * ?")//每天的8点定时执行
    public void scheduler(){
        System.out.println("@Scheduled每隔1s执行一次");
    }

}
