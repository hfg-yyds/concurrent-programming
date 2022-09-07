package com.hacker.example1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.example1.ThreadDemo1
 * @author: 韩福贵
 * @date: 2022-09-07
 * @version: 1.0
 */
public class ThreadDemo1 {
    CountDownLatch countDownLatch = new CountDownLatch(10000);
    private long count =0;
    @SneakyThrows
    @Test
    public void test1() {
        for (int i = 0; i < 2; i++) {
            new Thread(()->{
                countDownLatch.countDown();
                add10K();
            }).start();
        }
        System.out.println(count);
    }
    public void add10K() {
        int index =1;
        while (index<10000) {
            count+=1;
            index++;
        }
    }
}
