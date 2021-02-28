package com.catcher92.demo.springdemo.util;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public class UserUtil {

    private static int id;

    public static int getId() {
        return ++id;
    }
}
