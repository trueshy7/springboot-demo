package cn.edu.cdut.service.impl;

import cn.edu.cdut.mapper.UserMapper;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public String login(User user) {
        User targetUser = userMapper.getUserByUsername(user.getUsername());
        if (targetUser == null) {
            return "该用户不存在";
        }
        if (user.getPassword().equals(targetUser.getPassword())) {
            return "登录成功";
        }
        return "密码不正确";
    };

    @Override
    public List<User> find(User user) {
        List list=(List)userMapper.selectUserByCondition(user);
        return list;
    }
}
