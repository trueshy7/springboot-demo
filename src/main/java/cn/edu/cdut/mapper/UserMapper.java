package cn.edu.cdut.mapper;

import cn.edu.cdut.pojo.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

@Repository
// 可以在启动类加上mapperscan
public interface UserMapper extends BaseMapper<User> {

    Integer selectByUserName(String username);
}
