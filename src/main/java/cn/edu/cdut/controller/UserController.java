package cn.edu.cdut.controller;

import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//控制前面请求
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;
    @GetMapping("/login")
    public String login(String name,String password) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        return userService.login(user);
    }
    @GetMapping("/find")
    public List<User> find(@RequestParam(required = false) Integer id,
                           @RequestParam(required = false) String name,
                           @RequestParam(required = false) String password,
                           @RequestParam(required = false) String email,
                           @RequestParam(required = false) Integer age) {
        User user = new User();
        user.setUsername(name);
        user.setPassword(password);
        user.setEmail(email);
        user.setAge(age);
        return userService.find(user);
    }
}
