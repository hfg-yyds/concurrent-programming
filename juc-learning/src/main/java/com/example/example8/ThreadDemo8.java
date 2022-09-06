package com.example.example8;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *      JUC辅助类的使用
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example8.ThreadDemo8
 * @author: 韩福贵
 * @date: 2022-09-03
 * @version: 1.0
 */
@Slf4j
public class ThreadDemo8 {

    /*
     * CountDownLatch方法使用
     * 减少计数的辅助类
     * await方法：减少锁存器的计数，如果计数达到0，则释放所有等待的线程。
     *
     * 列子：六个同学离开教室之后,班长才能关门....
     */
    @SneakyThrows
    @Test
    public void test1() {
        //创建CountDownLatch对象并设置初始值....
        CountDownLatch countDownLatch = new CountDownLatch(6);

        //同学离开
        for (int i = 1; i <= 6; i++) {
            new Thread(() -> {
                countDownLatch.countDown();
                System.out.println(Thread.currentThread().getName() + "号同学离开教室");
            }, "" + i).start();
        }

        //班长锁门  如果当前计数大于0，那么当前线程将被禁用，并处于休眠状态
        countDownLatch.await();
        new Thread(() -> {
            System.out.println(Thread.currentThread().getName() + "锁门了");
        }, "班长").start();

    }

    /*
     * 循环栅栏CyclicBarrier辅助类使用
     *
     * 列子：集齐七颗龙珠就可以召唤神龙了
     */
    @Test
    public void test2() {
        //创建CyclicBarrier对象
        CyclicBarrier cyclicBarrier = new CyclicBarrier(7,()->{
            System.out.println("集齐七颗龙珠就可以召唤神龙了.....");
        });

        //集齐七颗龙珠的过程 --循环栅栏代码
        for (int i = 0; i < 7; i++) {
            new Thread(()->{
                System.out.println(Thread.currentThread().getName()+"星龙珠被收集到了...");
                try {
                    //等待
                    cyclicBarrier.await();
                } catch (Exception e) {
                    log.error(e.getMessage());
                }
            },""+i).start();
        }
    }

    /*
     * 信号灯 Semaphore
     *
     * 列子三：六量汽车停在三个位置上面
     */
    @Test
    public void test3() {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0; i < 6; i++) {
            new Thread(()->{
                try {
                    //抢占车位
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"号汽车开进车位了");

                try {
                    TimeUnit.SECONDS.sleep(new Random(5).nextInt());
                    System.out.println(Thread.currentThread().getName()+"号汽车离开车位了......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },""+i).start();
        }
    }



}