package com.hacker.service;

import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.service.Unit2Service
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
@Service
public class Unit2Service {

    public List<String> getPriceByStep(List<NetMall> list,String productName) throws InterruptedException {
        return list.stream().map(netMall -> String.format(productName+"in %s price is %.2f,net",netMall.getMallName()
        ,netMall.getPrice(productName))).collect(Collectors.toList());
    }

    /**
     * 异步,万箭齐发
     * @param list list
     * @param productName productName
     * @return List<String>
     * @throws InterruptedException
     */
    public List<String> getPriceByStepASyns(List<NetMall> list,String productName) throws InterruptedException {
        return list.stream().map(netMall -> CompletableFuture.supplyAsync(()->
                 String.format(productName+"in %s price is %.2f,net",netMall.getMallName(),netMall.getPrice(productName))))
                 .collect(Collectors.toList())
                 .stream().map(CompletableFuture::join)
                 .collect(Collectors.toList());
    }



}
