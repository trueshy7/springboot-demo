package cn.edu.cdut.controller;

import cn.edu.cdut.interceptor.TokenInterceptor;
import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

//控制前面请求
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public Result login(@RequestBody User user) {
        userService.login(user.getUsername(), user.getPassword());
        //生成一个唯一标识的字符串token，并且返回给前端。每次请求都要带上taken
        String token = UUID.randomUUID().toString().replaceAll("-", "");
        TokenInterceptor.tokenSet.add(token);
        Result result = new Result(200, "登陆成功", null);
        result.put("token", token);
        return result;
    }

    @PostMapping("/list")
    public Result getUserList(@RequestBody User user) {
        return Result.success(userService.getUserList(user));
    }

    @DeleteMapping("/delete")
    public Result deleteUser(@RequestParam("id") int id) {
        boolean deleted = userService.deleteUserById(id);
        if (deleted) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败，用户不存在");
        }
    }

    @PostMapping("/add")
    public Result addUser(@RequestBody User user) {
        if (!userService.addUser(user)) {
            return Result.error("添加失败");
        } else {
            return Result.success("添加成功");
        }
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody User user) {
        System.out.println(user);
        if (!userService.updateUser(user)) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }
}
