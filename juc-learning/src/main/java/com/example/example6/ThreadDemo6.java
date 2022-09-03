package com.example.example6;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      死锁代码的写法
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example.example6
 * @author: 韩福贵
 * @date: 2022-09-03
 * @version: 1.0
 */
public class ThreadDemo6 {
    private Object obj1= new Object();
    private Object obj2= new Object();

    @Test
    public void test1() {
        new Thread(()->{
            synchronized (obj1) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程AA持有锁obj1,想持有锁obj2");
                synchronized (obj2) {
                    System.out.println("线程AA获取到锁obj2");
                }
            }
        },"AA").start();
        new Thread(()->{
            synchronized (obj2) {
                System.out.println("线程AA持有锁obj2,想持有锁obj1");
                synchronized (obj1) {
                    System.out.println("线程AA获取到锁obj2");
                }
            }
        },"BB").start();
    }

}
