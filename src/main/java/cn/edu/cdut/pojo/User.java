package cn.edu.cdut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@TableName("users")//告诉mybatis-plus该类与哪张表关联
public class User {
    @TableId(type= IdType.AUTO)//TableId表示主键，括号内的参数表示自增长
    private Integer user_id;
    private String username;
    private Integer age;
    private String email;
    private String password;
    private String gender;
    private LocalDateTime create_time;
    private LocalDateTime last_login;
    private String status;
}
