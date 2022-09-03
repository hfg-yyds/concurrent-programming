package com.example.example7;

import java.util.concurrent.Callable;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.example.example7.ThreadCall
 * @author: 韩福贵
 * @date: 2022-09-03
 * @version: 1.0
 */
public class ThreadCall implements Callable<String> {

    @Override
    public String call() throws Exception {
        return "I love you !";
    }

}
