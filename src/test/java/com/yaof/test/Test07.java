package com.yaof.test;

import org.springframework.util.ObjectUtils;

import java.util.Random;

/**
 * @Author yaof
 * @Date 2021-04-22
 */
public class Test07 {

    private static int a;

    public static void main(String[] args) {
        System.out.println(ObjectUtils.isEmpty(a));

        System.out.println("12345678".substring(4));

        Random rd = new Random();
        System.out.println(rd.nextInt(3));

        Integer aa = null;
        if(aa > 0) {
            System.out.println("nul");
        }
    }
}
