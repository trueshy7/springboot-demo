package cn.edu.cdut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName("users")//告诉mybatis-plus该类与哪张表关联
@ToString
public class User {
    @TableId(type = IdType.AUTO,value = "user_id")//TableId表示主键，括号内的参数表示自增长
    private Integer userId;
    private String username;
    private Integer age;
    private String email;
    private String password;
    private String gender;
    private LocalDateTime createTime;
    private LocalDateTime lastLogin;
    private String status;
}
