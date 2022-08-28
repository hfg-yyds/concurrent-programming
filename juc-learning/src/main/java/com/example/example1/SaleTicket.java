package com.example.example1;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *      模拟三个售票员
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example1.SaleTicket
 * @author: 韩福贵
 * @date: 2022-08-28
 * @version: 1.0
 */
public class SaleTicket {

    @Test
    @SneakyThrows
    public void test() {
        Ticket ticket = new Ticket();
        new Thread(() -> {
            for (int i = 0; i <20; i++) {
                ticket.sale();
            }
        },"小红").start();
        new Thread(() -> {
            for (int i = 0; i <5; i++) {
                ticket.sale();
            }
        },"小男").start();
        new Thread(() -> {
            for (int i = 0; i <5; i++) {
                ticket.sale();
            }
        },"小天").start();
    }

}
