package cn.edu.cdut.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//控制前面请求
@RestController
public class UserController {
    @GetMapping("/hello")
    public String hello(String name, String password) {
        return "欢迎 " + name + " 访问, 你的密码是：" + password;
    }
}
