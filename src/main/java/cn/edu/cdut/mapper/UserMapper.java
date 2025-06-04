package cn.edu.cdut.mapper;

import cn.edu.cdut.pojo.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// 可以在启动类加上mapperscan
public interface UserMapper {
  List<User> selectUserByCondition(User user);
  User getUserById(Integer id);
  User  getUserByUsername(String username);
}
