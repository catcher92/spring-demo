package com.catcher92.demo.springdemo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("")
    public String login() {
        return "/login/login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(String username, String password, boolean rememberMe, HttpServletRequest request, Model model){
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken();
        String host = request.getRemoteHost();
        token.setUsername(username);
        token.setPassword(password.toCharArray());
        token.setHost(host);
        token.setRememberMe(rememberMe);
        String loginResult = null;
        try {
            subject.login(token);
        } catch (UnknownAccountException e) {
            loginResult = "账户不存在!";
        } catch (IncorrectCredentialsException e) {
            loginResult = "密码不正确!";
        } catch (LockedAccountException e) {
            loginResult = "账户被锁定!";
        } catch (AuthenticationException e) {
            loginResult = "登录异常!";
        }
        if (subject.isAuthenticated()) {
            return "redirect:/index";
        } else {
            model.addAttribute("loginResult", loginResult);
            return "index";
        }
    }

}
