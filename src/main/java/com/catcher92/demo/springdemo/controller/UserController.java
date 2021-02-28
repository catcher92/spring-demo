package com.catcher92.demo.springdemo.controller;

import com.catcher92.demo.springdemo.constant.UserConstant;
import com.catcher92.demo.springdemo.entity.User;
import com.catcher92.demo.springdemo.service.MongoUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
@RequestMapping("/user")
@RequiresRoles(value = {UserConstant.ROLES_USER, UserConstant.ROLES_ADMIN}, logical = Logical.OR)
public class UserController {

    private static final String preffix = "user/";

    @Autowired
    private MongoUserService userService;

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_ADD }, logical = Logical.OR)
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(){
        return preffix+"userAdd";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(User user){
        long userId = System.currentTimeMillis();
        user.setUserId(userId);
        user.setCreateBy((String) SecurityUtils.getSubject().getPrincipal());
        user.setCreateDate(new Date());
        userService.add(user);
        return "redirect:/user/get/"+userId;
    }

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_GET }, logical = Logical.OR)
    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Long id, Model model){
        User user = userService.find(id);
        model.addAttribute("user", user);
        return preffix+"userView";
    }

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_GET }, logical = Logical.OR)
    @RequestMapping("/getAll")
    public String getAll(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return preffix+"userList";
    }

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_UPDATE }, logical = Logical.OR)
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

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_UPDATE }, logical = Logical.OR)
    @RequestMapping(value = "/update/{id}", method = RequestMethod.POST)
    public String update(@PathVariable("id") Long id, User user, Model model){
        user.setUpdateBy((String) SecurityUtils.getSubject().getPrincipal());
        user.setUpdateDate(new Date());
        userService.update(user);
        model.addAttribute(user);
        return "redirect:/user/get/"+id;
    }

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_UPDATE }, logical = Logical.OR)
    @RequestMapping(value = "/audit/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String audit(@PathVariable("id") Long id){
        userService.updateAge(id);
        return "success";
    }

    @RequiresPermissions(value = {UserConstant.PERMISSIONS_USER_ALL, UserConstant.PERMISSIONS_USER_DELETE }, logical = Logical.OR)
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
    @ResponseBody
    public String del(@PathVariable("id") Long id){
        int del = userService.del(id);
        if (del > 0) {
            return "success";
        }
        return null;
    }
}
