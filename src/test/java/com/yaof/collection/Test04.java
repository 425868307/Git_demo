package com.yaof.collection;

import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author yaof
 * @Date 2021-04-12
 */
public class Test04 {

    public static void main(String[] args) {
        List<String> aaa = new ArrayList<>();
        aaa.add("a");
        aaa.add("b");
        aaa.add("a");
        aaa.add("d");
        aaa.add("e");
        System.out.println(JSON.toJSONString(aaa.subList(0,5)));

        Map<String, Long> collect = aaa.stream().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        System.out.println(JSON.toJSONString(collect));
    }
}
