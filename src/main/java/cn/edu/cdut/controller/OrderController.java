package cn.edu.cdut.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/order")
public class OrderController {
    @GetMapping("/hello")
    public String hello(String name , String password){
        return name+" hello "+ password;
    }
}
