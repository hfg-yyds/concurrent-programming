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
       /* if (number != 0) {
            this.wait(); //如果等于1 线程等待
        }*/
        /**
         * 在哪里睡,就在那里醒 三个即三个以上线程就会导致虚假唤醒
         * 所以使用while进判断 不能使用if
         * 因为while在当前线程醒了之后还是会进行判断 就不会出现线程虚假唤醒现象了
         */
        while (number != 0) {
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
        while (number != 1) {
            this.wait();
        }
        number--;
        log.info(Thread.currentThread().getName()+": {}",number);
        this.notifyAll();
    }

}
