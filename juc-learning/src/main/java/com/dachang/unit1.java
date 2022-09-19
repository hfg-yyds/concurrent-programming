package com.dachang;

import lombok.SneakyThrows;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.dachang.unit1
 * @author: 韩福贵
 * @date: 2022-09-19
 * @version: 1.0
 */
@Slf4j
public class unit1 {

    @Test
    public void test1() {
        new Thread(() -> {

        }, "AA").start();
    }

    @Test
    public void test2() throws InterruptedException, ExecutionException, TimeoutException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("-----come in FutureTask");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + ThreadLocalRandom.current().nextInt(100);
        });

        Thread t1 = new Thread(futureTask, "t1");
        t1.start();

        //3秒钟后才出来结果，还没有计算你提前来拿(只要一调用get方法，对于结果就是不见不散，会导致阻塞)
//        System.out.println(Thread.currentThread().getName()+"\t"+futureTask.get());

        //3秒钟后才出来结果，我只想等待1秒钟，过时不候
        try {
            System.out.println(Thread.currentThread().getName() + "\t" + futureTask.get(1L, TimeUnit.SECONDS));
        } catch (Exception e) {
            System.out.println("我就等你一秒钟,过时不候！！！");
        }

        System.out.println(Thread.currentThread().getName() + "\t" + " run... here");

    }

    /**
     * 使用轮询来替代阻塞
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        FutureTask<String> futureTask = new FutureTask<>(() -> {
            System.out.println("-----come in FutureTask");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "" + ThreadLocalRandom.current().nextInt(100);
        });

        new Thread(futureTask, "t1").start();

        System.out.println(Thread.currentThread().getName() + "\t" + "线程完成任务");

        /*
         * 用于阻塞式获取结果,如果想要异步获取结果,通常都会以轮询的方式去获取结果
         */
        while (true) {
            if (futureTask.isDone()) {
                System.out.println(futureTask.get());
                break;
            } else {
                //每秒轮询一次
                TimeUnit.SECONDS.sleep(1L);
                log.info("正在执行中....");
            }
        }
    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        CompletableFuture.runAsync(()->{
           log.info(Thread.currentThread().getName()+"\n"+"----------Come in....runAsync");
        });

        /*
         * 线程池用在什么地方...
         */
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());
        CompletableFuture.runAsync(()->{
            log.info(Thread.currentThread().getName()+"\n"+"----------Come in.....runAsync-executor");
        },executor);

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + "\n" + "----------Come in....supplyAsync");
            return "I  Love China";
        });
        System.out.println(supplyAsync.get());

        executor.shutdown();
    }

    @Test
    public void test5() throws ExecutionException, InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(1, 5, 1, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            log.info(Thread.currentThread().getName() + "\n" + "----------Come in....supplyAsync");
            try {
                TimeUnit.SECONDS.sleep(3L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "I  Love China";
        },executor).thenApply(x->{
            return x + "!!!!!";
        }).whenComplete((v,e)->{
            if (e == null) {
                System.out.println("没有发生异常...,结果："+v);
            }
        }).exceptionally(e->{
            log.error(e.getMessage());
            return null;
        });

        /*//阻塞吗？ 照样阻塞
        System.out.println(supplyAsync.get());*/

        System.out.println("-------Main Over!!!");
        TimeUnit.SECONDS.sleep(4L);
        executor.shutdown();
    }

}
