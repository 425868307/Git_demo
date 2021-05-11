package com.yaof.test;

import com.yaof.io.User;
import org.junit.Test;

import java.lang.reflect.Method;


public class Test05 {

    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader.getSystemClassLoader().loadClass("com.yaof.test.Test03");
//		Class.forName("com.yaof.test.Test03");
        User user = new User();
        user.setName("yaofffff");
        System.out.println(user);
        user.setName("dcs犯得上发生");
        user.setAge(23);
        System.out.println(user);

        Method[] method = Test05.class.getDeclaredMethods();
        for (Method m :
                method) {
            Test annotation = m.getAnnotation(Test.class);
            System.out.println(annotation);
        }
    }

}
