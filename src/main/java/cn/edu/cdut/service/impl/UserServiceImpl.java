package cn.edu.cdut.service.impl;

import cn.edu.cdut.mapper.UserMapper;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void login(String username, String password) {
        Map<String, Object> map = new HashMap<>();
        map.put("username", username);
        List<User> users = baseMapper.selectByMap(map);
        if (users.size() == 0) {
            throw new NullPointerException();
        }
        User user = users.get(0);
        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("密码错误");
        }
    }

    @Override
    public List<User> getUserList(User user) {
        Map<String, Object> map = new HashMap<>();
        if (user.getUsername() != null && user.getUsername() != "") {
            map.put("username", user.getUsername());
        }
        if (user.getEmail() != null && user.getEmail() != "") {
            map.put("email", user.getEmail());
        }
        if (user.getAge() != null) {
            map.put("age", user.getAge());
        }
        List<User> users = baseMapper.selectByMap(map);
        return users;
    }

    @Override
    public boolean deleteUserById(int id) {
        int deleted = baseMapper.deleteById(id);
        if (deleted > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUserById(Integer id) {
        User user = baseMapper.selectById(id);
        if (user == null) {
            throw new NullPointerException("用户不存在");
        }
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean addUser(User user) {
        if (user.getUsername() == null || user.getUsername() == "") {
            throw new RuntimeException("请输入用户名");
        }
        HashMap map = new HashMap();
        map.put("username", user.getUsername());
        List<User> users;
        users = baseMapper.selectByMap(map);
        if (!users.isEmpty()) {
            throw new RuntimeException("该用户名已被使用");
        }
        map.clear();
        map.put("email", user.getEmail());
        users = baseMapper.selectByMap(map);
        if (!users.isEmpty()) {
            throw new RuntimeException("该邮箱已经注册过了");
        }
        baseMapper.insert(user);
        return true;
    }

    @Override
    public boolean updateUser(User user) {
        HashMap map = new HashMap();
        map.put("username", user.getUsername());
        List<User> users = baseMapper.selectByMap(map);
        if (users.size() > 0 && !user.getUsername().equals(users.get(0).getUsername())) {
            throw new RuntimeException("用户已经存在了");
        }
        baseMapper.updateById(user);
        return true;
    }
}
