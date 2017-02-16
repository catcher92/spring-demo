package com.catcher92.demo.springdemo.util;

/**
 * Created by caoxuedong on 2017/2/16.
 */
public class RedisUtil {

    /**
     * <p>功能描述：根据类名和主键生成key</p>
     * @return 
     * @author caoxuedong
     * @date 2017/2/16 18:06
     */
    public static String getKey(Class cls, Integer id) {
        StringBuilder builder = new StringBuilder(cls.getSimpleName());
        if (null != id) {
            builder.append(":").append(id).toString();
        }
        return builder.toString();
    }

    public static String getKey(Class cls) {
        return getKey(cls, null);
    }

    public static String getHashKey(Class cls, Integer id) {
        StringBuilder builder = new StringBuilder(cls.getSimpleName()).append("_hash");
        if (null != id) {
            builder.append(":").append(id).toString();
        }
        return builder.toString();
    }

    public static String getListKey(Class cls, Integer id) {
        StringBuilder builder = new StringBuilder(cls.getSimpleName()).append("_list");
        if (null != id) {
            builder.append(":").append(id).toString();
        }
        return builder.toString();
    }
}
