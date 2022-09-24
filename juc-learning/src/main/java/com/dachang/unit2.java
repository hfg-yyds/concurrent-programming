package com.dachang;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.dachang.unit2
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
@Slf4j
public class unit2 {

    @Test
    public void test1() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "你好";
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("异步线程结果：" + v);
            }
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return null;
        });

        System.out.println("主线程结束......");
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(future.join());
    }


}
