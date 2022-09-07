package com.example.example11;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example11.ThreadDemo11
 * @author: 韩福贵
 * @date: 2022-09-07
 * @version: 1.0
 */
public class ThreadDemo11 {

    @Test
    public void test1() {
        /*
         * 一池N线程
         */
        Executors.newFixedThreadPool(10);
        /*
         * 一个任务一个任务执行  一池一线程
         */
        Executors.newSingleThreadExecutor();
        /*
         * 线程池根据需要创建线程
         */
        Executors.newCachedThreadPool();
    }

    @Test
    public void test2() {
        //一池五线程
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        //10个顾客请求
        for (int i = 0; i < 10; i++) {
            executorService.execute(() -> {
                System.out.println(Thread.currentThread().getName() + "正在办理业务！！");
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test3() {
        //一池一线程
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "正在办理业务！！");
            });
        }
        executorService.shutdown();
    }

    @Test
    public void test4() {
        //一池可扩容的线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 20; i++) {
            executorService.execute(()->{
                System.out.println(Thread.currentThread().getName() + "正在办理业务！！");
            });
        }
        executorService.shutdown();
    }

}