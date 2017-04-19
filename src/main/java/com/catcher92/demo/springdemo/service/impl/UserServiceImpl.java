package com.catcher92.demo.springdemo.service.impl;

import com.catcher92.demo.springdemo.entity.User;
import com.catcher92.demo.springdemo.util.UserUtil;
import com.catcher92.demo.springdemo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Service
public class UserServiceImpl implements UserService{

    private static Map<Long, User> users = new HashMap<>();

    public Long add(User user) {
        Long id = UserUtil.getId();
        user.setId(id);
        users.put(id, user);
        return id;
    }

    public int del(Long id) {
        users.remove(id);
        return 1;
    }

    public User find(Long id) {
        return users.get(id);
    }

    public List<User> findAll() {
        List<User> list = new ArrayList<>();
        list.addAll(users.values());
        return list;
    }

    public int update(User user) {
        users.put(user.getId(), user);
        return 1;
    }
}
