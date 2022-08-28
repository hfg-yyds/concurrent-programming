package com.example.example1;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *      票据资源类 专门负责买票
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example1.Ticket
 * @author: 韩福贵
 * @date: 2022-08-28
 * @version: 1.0
 */

@Slf4j
public class Ticket {
    /**
     * 可重入锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    /**
     * 资源属性 票的张数
     */
    private Integer number = 30;

    /**
     * 操作方法 买票
     */
    public synchronized void sale() {
        if (number > 0) {
            number--;
            log.info(Thread.currentThread().getName()+"卖了一张票"+" 还剩{}张票",number);
        }
    }

    /**
     * 操作方法 买票
     */
    public  void saleLock() {
        //上锁
        lock.lock();
        try {
            if (number > 0) {
                number--;
                log.info(Thread.currentThread().getName()+"卖了一张票"+" 还剩{}张票",number);
            }
        } finally {
            /**
             * 这样写的原因是如果出现异常也要释放当前线程的锁
             * 否则就会阻塞该线程
             */
            lock.unlock();
        }
    }

}
