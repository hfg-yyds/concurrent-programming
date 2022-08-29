package com.example.example3;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      线程自定义通信方式
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example3.SharedResources
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
@Slf4j
public class SharedResources {
    //定义标志位
    private Integer flag = 1;

    private final Lock lock = new ReentrantLock();

    private final Condition condition1 = lock.newCondition();

    private final Condition condition2 = lock.newCondition();

    private final Condition condition3 = lock.newCondition();

    @SneakyThrows
    public void print5(Integer loop) {
       lock.lock();
       try {
           //先判断
           while (flag != 1) {
               condition1.await();
           }
           //干活
           for (int i = 0; i < 5; i++) {
               log.info(Thread.currentThread().getName()+"打印第{}轮",loop);
           }
           //通知
           flag = 2;  //先修改标志位
           condition2.signal();
       } finally {
           lock.unlock();
       }
    }

    @SneakyThrows
    public void print10(Integer loop) {
        lock.lock();
        try {
            //先判断
            while (flag != 2) {
                condition2.await();
            }
            //干活
            for (int i = 0; i < 10; i++) {
                log.info(Thread.currentThread().getName()+"打印第{}轮",loop);
            }
            //通知
            flag = 3;  //先修改标志位
            condition3.signal();
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public void print15(Integer loop) {
        lock.lock();
        try {
            //先判断
            while (flag != 3) {
                condition3.await();
            }
            //干活
            for (int i = 0; i < 15; i++) {
                log.info(Thread.currentThread().getName()+"打印第{}轮",loop);
            }
            //通知
            flag = 1;  //先修改标志位
            condition1.signal();
        } finally {
            lock.unlock();
        }
    }


}
