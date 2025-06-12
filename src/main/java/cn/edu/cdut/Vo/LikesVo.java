package cn.edu.cdut.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LikesVo {
    private Integer likeId;
    private String username;
    private String title;
    private LocalDateTime likeTime;
}
