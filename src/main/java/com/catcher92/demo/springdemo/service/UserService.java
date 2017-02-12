package com.catcher92.demo.springdemo.service;

import com.catcher92.demo.springdemo.Entity.User;

import java.util.List;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public interface UserService {

    public int add(User user);

    public int del(int id);

    public User find(int id);

    public List<User> findAll();

    public int update(User user);
}
