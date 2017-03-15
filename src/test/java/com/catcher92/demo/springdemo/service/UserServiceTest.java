package com.catcher92.demo.springdemo.service;

import com.catcher92.demo.springdemo.Entity.User;
import com.catcher92.demo.springdemo.util.UserUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by caoxuedong on 2017/3/14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/applicationContext.xml"})
@WebAppConfiguration
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void findAll() throws Exception {
        User user = new User();
        user.setId(UserUtil.getId());
        user.setAge((short) 1);
        user.setName("张三");
        userService.add(user);
        List<User> users = userService.findAll();
        System.out.println(users);
    }

}