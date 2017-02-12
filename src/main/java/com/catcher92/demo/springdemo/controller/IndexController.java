package com.catcher92.demo.springdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by caoxuedong on 2017/2/10.
 */
@Controller
public class IndexController {

    @RequestMapping(value = {"","index"})
    public String index(HttpServletRequest request, Model model){
        String ip = request.getRemoteAddr();
        model.addAttribute("message", new StringBuilder("ip:").append(ip));
        return "index";
    }

}
