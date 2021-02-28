package com.catcher92.demo.springdemo.service.impl;

import com.catcher92.demo.springdemo.entity.User;
import com.catcher92.demo.springdemo.util.RedisUtil;
import com.catcher92.demo.springdemo.util.UserUtil;
import com.catcher92.demo.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Service
public class UserServiceImpl implements UserService{

    private static Map<Integer, User> users = new HashMap<>();
    @Autowired
    private RedisTemplate template;

    public int add(User user) {
        int id = UserUtil.getId();
        user.setUserId(Long.valueOf(id));
        users.put(id, user);
        template.opsForValue().set(RedisUtil.getKey(User.class, user.getUserId().intValue()), user, 5, TimeUnit.MINUTES);
        return id;
    }

    public int del(int id) {
        try {
            Thread.sleep(50);
            template.delete(RedisUtil.getKey(User.class, id));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        users.remove(id);
        return 1;
    }

    public User find(int id) {
        User user = (User) template.opsForValue().get(RedisUtil.getKey(User.class, id));
        if (null == user) {
            user = users.get(id);
            if (null != user) {
                template.opsForValue().set(RedisUtil.getKey(User.class, user.getUserId().intValue()), user, 5, TimeUnit.MINUTES);
            }
        }
        return user;
    }

    public List<User> findAll() {
        final List<User> list = new ArrayList<>();
        list.addAll(users.values());
        list.forEach(user -> {
            // 添加list
            template.opsForList().leftPush(RedisUtil.getListKey(User.class, 0), user);
            // 添加hash
            template.opsForHash().put(RedisUtil.getHashKey(User.class, 0), RedisUtil.getKey(User.class, user.getUserId().intValue()), user);
        });
        return list;
    }

    public int update(User user) {
        users.put(user.getUserId().intValue(), user);
        template.delete(RedisUtil.getKey(User.class, user.getUserId().intValue()));
        return 1;
    }
}
