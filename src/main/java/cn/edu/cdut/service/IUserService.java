package cn.edu.cdut.service;

import cn.edu.cdut.pojo.User;

import java.util.List;

public interface IUserService {
    String login(User user);
    List<User> find(User user);
}
