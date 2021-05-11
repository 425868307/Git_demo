package com.yaof.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author yaof
 * @Date 2021-04-13
 */
public class Test02 {
    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<>();
        map.put("aaa", "a1");
        map.put("bbb", "a2");
        map.put("ccc", "a3");
        map.put("ddd", "a4");

        List<String> list = new ArrayList<>();
        list.add("a1");
        list.add("a2");
        list.add("a3");
        list.add("a4");
        List<String> a = list.parallelStream().filter(ll -> ll.compareTo("a2") > 0).collect(Collectors.toList());
        System.out.println(a.size());
    }
}
