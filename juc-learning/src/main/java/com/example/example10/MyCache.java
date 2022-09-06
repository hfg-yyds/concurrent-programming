package com.example.example10;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example10.MyCache
 * @author: 韩福贵
 * @date: 2022-09-06
 * @version: 1.0
 */
@Slf4j
public class MyCache {

    private volatile ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private final Lock writeLock = readWriteLock.writeLock();
    private final Lock readLock = readWriteLock.readLock();
    /**
     * 创建资源map
     */
    private final Map<String,Object> map = new HashMap<>();

    /**
     * 放数据
     * @param key key
     * @param value value
     */
    @SneakyThrows
    public void put(String key, Object value) {
        writeLock.lock();
        try {
            log.info(Thread.currentThread().getName()+"正在写"+key);
            TimeUnit.SECONDS.sleep(1);
            map.put(key,value);
            log.info(Thread.currentThread().getName()+"写完了"+key);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 取数据
     * @param key key
     * @return obj
     */
    @SneakyThrows
    public Object get(String key) {
        readLock.lock();
        Object result = null;
        try {
            log.info(Thread.currentThread().getName()+"正在读"+key);
            TimeUnit.SECONDS.sleep(3);
            result = map.get(key);
            log.info(Thread.currentThread().getName()+"读完了"+result);
        } catch (Exception e) {
            log.error(e.getMessage());
        } finally {
            readLock.unlock();
        }
        return result;
    }


}
