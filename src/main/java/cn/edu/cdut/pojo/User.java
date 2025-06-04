package cn.edu.cdut.pojo;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private String username;
    private Integer age;
    private String email;
    private String password;
}
