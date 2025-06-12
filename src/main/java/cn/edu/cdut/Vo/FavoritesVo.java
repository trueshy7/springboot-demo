package cn.edu.cdut.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class FavoritesVo {
    private Integer favoriteId;
    private String username;
    private String title;
    private LocalDateTime favoriteTime;
}
