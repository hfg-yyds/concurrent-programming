package com.example.example5;

import org.junit.jupiter.api.Test;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      可重入锁的Demo演示
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example5.ThreadDemo5
 * @author: 韩福贵
 * @date: 2022-09-01
 * @version: 1.0
 */
public class ThreadDemo5 {

    public synchronized void add() {
        add();
    }

    /**
     * 隐式重入锁的应用
     */
    @Test
    public void test1() {
        ThreadDemo5 threadDemo5 = new ThreadDemo5();
        threadDemo5.add();
    }

    @Test
    public void test2() {
        Lock lock = new ReentrantLock(true);
        ThreadDemo5 threadDemo5 = new ThreadDemo5();
        try {
            lock.lock();
            try {
                lock.lock();
            }finally {
                lock.unlock();
            }
        } finally {
            lock.unlock();
        }
    }


}
