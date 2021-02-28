package com.catcher92.demo.springdemo.service;

import com.catcher92.demo.springdemo.entity.User;

import java.util.List;

/**
 * Created by caoxuedong on 2017/5/6.
 */
public interface MongoUserService {

    int updateAge(Long id);

    int del(Long id);

    User find(Long id);

    long add(User user);

    List<User> findAll();

    int update(User user);
}
