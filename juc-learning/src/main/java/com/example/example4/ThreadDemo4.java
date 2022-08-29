package com.example.example4;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

/**
 * <p>
 *      模拟ArrayList来演示并发情况下ArrayList的Add方法异常
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example4.ThreadDemo4
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
public class ThreadDemo4 {

    @Test
    public void test1() {
        //创建ArrayList
        ArrayList<String> list = new ArrayList<>();

        for (int i = 0; i < 1000; i++) {
            new Thread(()->{
                //向集合添加内容
                list.add("1");
                //从集合获取内容
                System.out.println(list.get(0));
            },"线程"+i).start();
        }
    }
}
