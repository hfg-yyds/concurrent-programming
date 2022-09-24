package com.hacker.service;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;

import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.service.NetMall
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
public class NetMall {

    private String mallName;

    public String getMallName() {
        return mallName;
    }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public NetMall(String mallName) {
        this.mallName = mallName;
    }

    public Double getPrice(String productName) {
        try {
            TimeUnit.SECONDS.sleep(1);
        }catch (Exception e) {
            e.printStackTrace();
        }
        return ThreadLocalRandom.current().nextDouble()*2 + productName.charAt(0);
    }
}
