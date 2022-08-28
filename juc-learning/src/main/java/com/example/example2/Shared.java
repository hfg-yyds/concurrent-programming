package com.example.example2;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *      资源类
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example2.Shared
 * @author: 韩福贵
 * @date: 2022-08-29
 * @version: 1.0
 */
@Slf4j
public class Shared {

    //资源属性
    private Integer number = 0;

    /**
     * +1 方法
     */
    @SneakyThrows
    public synchronized void add() {
        //第二步 判断
        if (number != 0) {
            this.wait(); //如果等于1 线程等待
        }
        //第二步 干活 如果number值是0 ,就+1操作
        number++;
        log.info(Thread.currentThread().getName()+": {}",number);

        //第二步 通知 唤醒其他的线程
        this.notifyAll();
    }

    /**
     * -1 方法
     */
    @SneakyThrows
    public synchronized void incr() {
        if (number != 1) {
            this.wait();
        }
        number--;
        log.info(Thread.currentThread().getName()+": {}",number);
        this.notifyAll();
    }

}
