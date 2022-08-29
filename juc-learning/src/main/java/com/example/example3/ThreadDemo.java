package com.example.example3;

import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example3.ThreadDemo
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
public class ThreadDemo {

    @Test
    public void test1() {
        SharedResources resources = new SharedResources();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resources.print5(i+1);
            }
        },"AA").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resources.print10(i+1);
            }
        },"BB").start();
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                resources.print15(i+1);
            }
        },"CC").start();
    }
}
