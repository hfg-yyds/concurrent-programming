package com.example.example2;

import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example2.ThreadDemo1
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
public class ThreadDemo1 {

    @Test
    public void test() {
        Shared shared = new Shared();

        //线程一
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shared.add();
            }
        },"ADD").start();

        //线程二
        new Thread(()->{
            for (int i = 0; i < 10; i++) {
                shared.incr();
            }
        },"INCR").start();
    }

}
