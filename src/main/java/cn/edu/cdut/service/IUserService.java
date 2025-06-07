package cn.edu.cdut.service;

import cn.edu.cdut.pojo.User;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface IUserService extends IService<User> {
    void login(String username, String password);
    List<User> getUserList(User user);
    boolean deleteUserById(int id);
    boolean addUser(User user);
    boolean updateUser(User user);
}
