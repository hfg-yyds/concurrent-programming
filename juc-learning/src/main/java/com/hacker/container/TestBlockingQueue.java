package com.hacker.container;

import org.junit.jupiter.api.Test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.container.TestBlockingQueue
 * @author: 韩福贵
 * @date: 2022-08-20
 * @version: 1.0
 */
public class TestBlockingQueue {

    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10,false);
        arrayBlockingQueue.put("a");
        arrayBlockingQueue.put("b");
        arrayBlockingQueue.put("c");
        arrayBlockingQueue.put("d");

        System.out.println(arrayBlockingQueue.take());
        System.out.println(arrayBlockingQueue.take());

    }

    @Test
    public void testLinkedBlockingQueue() throws InterruptedException {
        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();

        linkedBlockingQueue.put("a");

        System.out.println(linkedBlockingQueue.take());


        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);
    }

    @Test
    public void test1() {
        ConcurrentSkipListMap<String, String> skipListMap = new ConcurrentSkipListMap<>();
        skipListMap.put("name","韩福贵");
        skipListMap.put("age","23");

        for (String s : skipListMap.keySet()) {
            System.out.println(skipListMap.get(s));
        }
    }
}
