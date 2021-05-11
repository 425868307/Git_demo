package com.yaof.thread;

import com.yaof.io.User;
import reactor.core.publisher.Flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

/**
 * @Author yaof
 * @Date 2021-04-08
 */

public class Test04 {

    public static void main(String[] args) throws Exception{
        //整型
        Flux<Integer> integerFlux = Flux.just(1, 2, 3, 4, 5);
        //字符串
        Flux<String> stringFlux = Flux.just("hello", "world");
        List<String> list = Arrays.asList("hello", "world");
        //列表
        Flux<String> stringFlux1 = Flux.fromIterable(list);
        //范围
        Flux<Integer> integerFlux1 = Flux.range(1, 5);
        //时间间隔
        Flux<Long> longFlux = Flux.interval(Duration.ofMillis(1000), Duration.ofMillis(2000));
        longFlux.subscribe(System.out::println);

        //Flux 创建
        Flux<String> stringFlux2 = Flux.from(stringFlux1);
        stringFlux2.subscribe(System.out::println);

        Thread.sleep(Long.MAX_VALUE);
    }
}
