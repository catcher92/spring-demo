package com.catcher92.demo.springdemo.service;

import com.catcher92.demo.springdemo.entity.User;

import java.util.List;

/**
 * Created by caoxuedong on 2017/2/10.
 */
public interface UserService {

    public Long add(User user);

    public int del(Long id);

    public User find(Long id);

    public List<User> findAll();

    public int update(User user);
}
