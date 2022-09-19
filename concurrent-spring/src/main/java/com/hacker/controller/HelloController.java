package com.hacker.controller;

import com.hacker.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.controller.HelloController
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
@RequestMapping("/hello")
@RestController
public class HelloController {

    @GetMapping("/get")
    @ApiOperation(value = "测试")
    public R<String> hello() {
        return R.ok("Hello world ！！");
    }

}
