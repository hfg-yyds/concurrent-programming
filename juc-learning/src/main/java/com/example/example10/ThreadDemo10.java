package com.example.example10;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      读写锁
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example10.ThreadDemo10
 * @author: 韩福贵
 * @date: 2022-09-06
 * @version: 1.0
 */
public class ThreadDemo10 {


    @Test
    @SneakyThrows
    public void test1() {
        MyCache myCache = new MyCache();

        //创建线程放数据
        for (int i = 0; i < 5; i++) {
            final Integer num = i;
            new Thread(()->{
                myCache.put(num+"",num+"AA");
            },String.valueOf(i)).start();
        }

        /*
         * 为啥加：若先发送读 会导致写线程全部在读线程之后
         */
        Thread.sleep(2000);

        //创建线程取数据
        for (int i = 0; i < 5; i++) {
            final Integer num = i;
            new Thread(()->{
                myCache.get(num+"");
            },String.valueOf(i)).start();
        }

        TimeUnit.MINUTES.sleep(1);
    }

}