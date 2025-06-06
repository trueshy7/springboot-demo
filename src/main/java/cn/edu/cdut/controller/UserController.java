package cn.edu.cdut.controller;

import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//控制前面请求
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {
    @Autowired
    private IUserService userService;
    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        userService.login(user.getUsername(),user.getPassword());
        return new Result(200,"登陆成功",null);
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
