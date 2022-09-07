package com.example.example11;

import java.util.concurrent.*;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example11.DefineThreadPool
 * @author: 韩福贵
 * @date: 2022-09-07
 * @version: 1.0
 */
public class DefineThreadPool {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5,
                20,
                2L,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.execute(()->{

        });
    }
}
