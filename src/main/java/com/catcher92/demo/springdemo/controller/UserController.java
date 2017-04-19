package com.catcher92.demo.springdemo.controller;

import com.catcher92.demo.springdemo.common.controller.BaseController;
import com.catcher92.demo.springdemo.entity.Page;
import com.catcher92.demo.springdemo.entity.User;
import com.catcher92.demo.springdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
@RequestMapping("/user")
public class UserController extends BaseController{

    private static final String preffix = "user/";

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return preffix+"userAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String add(User user){
        Long id = userService.add(user);
        if (id != null) {
            return "操作成功";
        }
        return "操作失败";
    }

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Long id, Model model){
        User user = userService.find(id);
        model.addAttribute("user", user);
        return preffix+"userView";
    }

    @RequestMapping(value = {"","index","/getAll"})
    public String getAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return preffix+"userList";
    }

    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public Page getAll (@RequestParam(required = false, defaultValue = "10") int limit, @RequestParam(required = false, defaultValue = "0")int offset){
        List<User> users = userService.findAll();
        Page page = new Page();
        page.setTotal(100);
        page.setRows(users);
        return page;
    }

    @RequestMapping(value = "/update/{id}", method = RequestMethod.GET)
    public String update(@PathVariable("id") Long id, Model model){
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
        userService.update(user);
        model.addAttribute(user);
        return "redirect:/user/get/"+id;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String delete(@PathVariable("id") Long id){
        userService.del(id);
        return "redirect:/user/index";
    }
}
