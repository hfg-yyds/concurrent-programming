package com.example.example2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      使用Lock进行线程之间的通信
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example2.ShareLock
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
@Slf4j
public class ShareLock {

    private int number = 0;

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    @SneakyThrows
    public void add() {
        lock.lock();
        try {
            //判断
            while (number != 0) {
                condition.await();
            }
            //干活
            number++;
            log.info(Thread.currentThread().getName()+": {}",number);
            //唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    @SneakyThrows
    public void incr() {
        lock.lock();
        try {
            //判断
            while (number != 1) {
                condition.await();
            }
            //干活
            number --;
            log.info(Thread.currentThread().getName()+": {}",number);
            //唤醒
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
