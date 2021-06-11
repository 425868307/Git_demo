package com.yaof.collection;

import com.alibaba.fastjson.JSON;
import com.yaof.mq.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author yaof
 * @Date 2021-05-26
 */
public class Test03 {

    public static void main(String[] args) {
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(11101);
        user1.setName("张三");
        users.add(user1);

        User user2 = new User();
        user2.setId(11102);
        user2.setName("李四");
        users.add(user2);

        User user3 = new User();
        user3.setId(11102);
        user3.setName("办得幔");
        users.add(user3);

//        Map<Integer, User> collect = users.stream().collect(Collectors.toMap(User::getId, user -> user));
//        collect.entrySet().forEach(entry -> entry.getValue().setAddress("上海市"));

        Map<String, Long> collect1 = users.stream().collect(Collectors.groupingBy(User::getName, Collectors.counting()));
        System.out.println(JSON.toJSONString(collect1));
    }
}
