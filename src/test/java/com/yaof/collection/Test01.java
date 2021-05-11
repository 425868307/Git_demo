package com.yaof.collection;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @Author yaof
 * @Date 2021-04-12
 */
public class Test01 {

    public static void main(String[] args) {
        Vector<String> vv = new Vector<>();
        vv.add("aaa");
        vv.add("aab");
        vv.add("aac");
        vv.add("aad");
        vv.forEach(System.out::println);

        //stack继承Vector,自己的方法可以实现先进后出的逻辑
        Stack<String> ss = new Stack<>();
        ss.push("sa");
        ss.push("sb");
        ss.push("sc");
        ss.push("sd");
        ss.push("se");
        System.out.println(ss.pop());

        Stream<String> stream = vv.stream();
        Stream<String> parallelStream = vv.parallelStream();

        try {
            InputStream is  = new FileInputStream("D://test.txt");

            File file = new File("d://test.txt");
            Stream<String> fs = Files.lines(Paths.get("d://test.txt"));
            fs.forEach(System.out::println);
            Stream<String> fs1 = Files.lines(Paths.get("d://test.txt"));
            Optional<String> max = fs1.max((aa, bb) -> aa.compareTo(bb));
            System.out.println(max.get());

            Stream<String> fs2 = Files.lines(Paths.get("d://test.txt"));
            Optional<String> reduce = fs2.reduce((aa, bb) -> aa + bb);
            System.out.println(reduce.get());

            Optional<String> xyz = Optional.of("xyz");
            xyz.ifPresent(System.out::println);
//            System.out.println(xyz.get());

            IntStream ints = Arrays.stream(new int[]{6, 5, 3, 7, 14, 63, 3, 1, 54, 62});
            IntSummaryStatistics iss = ints.summaryStatistics();
            System.out.println(iss.getMax());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
