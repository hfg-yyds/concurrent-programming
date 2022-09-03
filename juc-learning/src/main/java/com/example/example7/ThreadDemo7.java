package com.example.example7;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      多线程接口使用
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example7.ThreadDemo7
 * @author: 韩福贵
 * @date: 2022-09-03
 * @version: 1.0
 */
public class ThreadDemo7 {
    @Test
    @SneakyThrows
    public void test7() {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            TimeUnit.SECONDS.sleep(10);
            return "1023";
        });
        new Thread(futureTask,"线程AA").start();
        System.out.println("试图获取线程执行结果......");
        if (!futureTask.isDone()) {
            System.out.println(futureTask.get());
        }
    }

    @Test
    public void test2() {

    }

}
