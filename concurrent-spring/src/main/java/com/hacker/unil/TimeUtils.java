package com.hacker.unil;

import java.util.concurrent.TimeUnit;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.unil.TimeUtils
 * @author: 韩福贵
 * @date: 2022-09-21
 * @version: 1.0
 */
public class TimeUtils {

    public static void sleep(long seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

}
