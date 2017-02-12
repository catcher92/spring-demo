package com.catcher92.demo.springdemo.controller;

import com.catcher92.demo.springdemo.Entity.User;
import com.catcher92.demo.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    private static final String preffix = "user/";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return preffix+"userAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user){
        int id = userService.add(user);
        return "redirect:/user/get/"+id;
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") int id, Model model){
        User user = userService.find(id);
        model.addAttribute("user", user);
        return preffix+"userView";
    }

    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return preffix+"userList";
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") int id, Model model){
        User user = userService.find(id);
        if (user == null) {
            return preffix+"userAdd";
        } else {
            model.addAttribute("user", user);
            return preffix+"userUpdate";
        }
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") int id, User user, Model model){
        userService.del(id);
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userService.update(user);
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        model.addAttribute(user);
        return "redirect:/user/get/"+id;
    }
}