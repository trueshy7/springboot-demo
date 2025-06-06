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
        Map<String,Object> map=new HashMap<>();
        map.put("username",username);
        List<User> users=baseMapper.selectByMap(map);
        if(users.size()==0) {
            throw  new NullPointerException();
        }
        User user=users.get(0);
        System.out.println(user);
        if(!user.getPassword().equals(password)){
            throw  new RuntimeException("密码错误");
        }
    }

    @Override
    public List<User> find(User user) {
        Map<String,Object> map=new HashMap<>();
        if(user.getUsername()!=null){
            map.put("username",user.getUsername());
        }
        if(user.getPassword()!=null){
            map.put("password",user.getPassword());
        }
        if(user.getId()!=null){
            map.put("id",user.getId());
        }
        if(user.getEmail()!=null){
            map.put("email",user.getEmail());
        }
        if(user.getAge()!=null){
            map.put("age",user.getAge());
        }
        List<User> users=baseMapper.selectByMap(map);
        return users;
    }
}
