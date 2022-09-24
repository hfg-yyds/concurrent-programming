package com.hacker.controller;

import com.google.common.collect.Lists;
import com.hacker.domain.Response;
import com.hacker.result.R;
import com.hacker.service.NetMall;
import com.hacker.service.Unit2Service;
import com.hacker.unil.TimeUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *
 * </p>
 * Copyright @2017-2022
 *
 * @moduleName: concurrent-programming
 * @className: com.hacker.controller.Unit2Controller
 * @author: 韩福贵
 * @date: 2022-09-20
 * @version: 1.0
 */
@Slf4j
@Api(value = "CompletableFuture类的常见使用")
@RequestMapping("/unit2")
@RestController
public class Unit2Controller {

    @Resource
    private Unit2Service unit2Service;

    @GetMapping("/test1")
    @ApiOperation(value = "复习CompletableFuture类")
    public R<String> test1() {
        List<String> list = new ArrayList<>();
        list.add("1-2-3");
        list.add("4-5-6");
        list.add("7-8-9");
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            //模拟耗时操作
            for (String s : list) {
                String[] split = s.split("-");
                System.out.println(split[2]);
            }
            return "你好";
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("异步线程结果：" + v);
            }
        }).exceptionally(e -> {
            System.out.println(e.getMessage());
            return e.getMessage();
        });

        System.out.println("主线程结束......");
        return R.ok(future.join());
    }


    @ApiOperation("商城比价需求")
    @GetMapping("/test2")
    public R<String> test2() throws InterruptedException {
        List<NetMall> mallList = Lists.newArrayList(new NetMall("jd"),
                new NetMall("tm"),
                new NetMall("pdd"));
        long start = System.currentTimeMillis();
        List<String> mysql = unit2Service.getPriceByStep(mallList, "mysql");
        long middle = System.currentTimeMillis();
        List<String> mysqlSyns = unit2Service.getPriceByStepASyns(mallList, "mysql");
        long end = System.currentTimeMillis();
        return R.ok(String.format("同步耗时:%s  异步耗时:%s",(middle-start),(end - middle)));
    }

    @ApiOperation("future获取结果方法")
    @GetMapping("/test3")
    public R<String> test3() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(0);
            return "Hanfugui";
        });
        TimeUtils.sleep(1);
        String result;
//        result = future.join();
//        result = future.get();
//        result = future.getNow("123456");
        //打断成功返回默认值  打断失败返回计算的值
        if (future.complete("1")) {
            result = future.join();
        }else {
            result = future.join();
        }
        return R.ok(result);
    }

    @ApiOperation("测试thenApply函数")
    @GetMapping("/test4")
    public R<Integer> test4() {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 1;
        }).thenApply(f -> {
            return f + 2;
        }).thenApply(f -> {
            int i = 10/0;
            return f + 3;
        }).thenApply(f -> {
            return f + 3;
        }).whenCompleteAsync((x,e)->{
            if (e == null) {
                System.out.println("线程串行化结果："+x);
                return;
            }
            System.out.println(e.getMessage());
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return 666666;
        });
        return R.ok(future.join());
    }


    @ApiOperation(value = "测试Handle方法")
    @GetMapping("/test5")
    public R<Integer> test5() {
        CompletableFuture<Integer> handle = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一步");
            return 1;
        }).handle((v, e) -> {
            System.out.println("第二步");
            int i = 10 / 0;
            return v+i;
        }).handle((v, e) -> {
            System.out.println("第三步");
            return v+3;
        }).whenComplete((v,e)->{
            if (e == null) {
                System.out.println("线程串行化结果："+v);
            }
        }).exceptionally(e->{
            System.out.println(e.getMessage());
            return 666666;
        });
        return R.ok(handle.join());
    }

    @ApiOperation("测试thenApply方法")
    @GetMapping("/test6")
    public R test6() {
        CompletableFuture<Void> future = CompletableFuture.supplyAsync(() -> {
            System.out.println("第一步");
            return 1;
        }).thenAccept(f -> {
            System.out.println(f+4);
        });
        return R.ok(future.join());
    }

    @ApiOperation(value = "测试applyToEither方法",notes = "谁快返回谁的结果！！！")
    @GetMapping("/test7")
    public R test7() {
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(2);
            System.out.println("线程一");
            System.out.println("线程一当前线程名字："+Thread.currentThread().getName());
            return "线程一";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(1);
            System.out.println("线程二");
            System.out.println("线程二当前线程名字："+Thread.currentThread().getName());
            return "线程二";
        });
        CompletableFuture<String> apply = future.applyToEither(future2, f -> {
            System.out.println("当前线程名字：" + Thread.currentThread().getName());
            return f + "快一点！！";
        });
        return R.ok(apply.join());
    }

    @ApiOperation("测试thenCombine方法")
    @GetMapping("/test8")
    public R<Response> test8() {
        long start= System.currentTimeMillis();
        AtomicReference<Response> response = new AtomicReference<>(new Response());
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(2);
            Response response1 = getResponse();
            response.set(response1);  //模拟外调获取相应的结果
            return "线程一";
        }).exceptionally((e)->{
            return "线程一异常";
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(4);
            return 24;
        });
        CompletableFuture<Response> combine = future1.thenCombine(future2, (x, y) -> {
            Response response1 = response.get();
            if (response1 == null) {
                throw new IllegalArgumentException("接口外调失败");
            }
            if (response1.getCode().equals("000000")) {
                System.out.println("成功。。。。");
                response1.setReturnMsg(x+y);
            }
            return response1;
        });
        Response response1 = null;
        try {
            response1 = combine.get(20, TimeUnit.SECONDS);
        }catch (CancellationException | ExecutionException | InterruptedException e) {
            System.out.println("future被取消、future异常完成、future在等待时被中断"+e.getMessage());
        }catch (TimeoutException e) {
            System.out.println("等待超时...");
        } catch (Exception e) {
            System.out.println("获取结果"+e.getMessage());
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
        return R.ok(response1);
    }

    private Response getResponse() {
        return new Response("000000","成功");
    }

    @ApiOperation("测试thenCombine方法")
    @GetMapping("/test9")
    public R<String> test9() {
        long start= System.currentTimeMillis();
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 1");
            TimeUtils.sleep(1);
            return 10;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(2);
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 2");
            return 20;
        }), (x,y) -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 3");
            return x + y;
        }).thenCombine(CompletableFuture.supplyAsync(() -> {
            TimeUtils.sleep(3);
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 4");
            return 30;
        }),(a,b) -> {
            TimeUtils.sleep(2);
            System.out.println(Thread.currentThread().getName() + "\t" + "---come in 5");
            return a + b;
        });
        Integer join = future.join();
        long end= System.currentTimeMillis();
        return R.ok("结果："+join+"耗时："+String.valueOf(end - start));
    }

}
