package com.hacker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.Applicaction
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
@EnableAsync
@SpringBootApplication
public class Applicaction {
    public static void main(String[] args) {
        SpringApplication.run(Applicaction.class,args);
    }
}
