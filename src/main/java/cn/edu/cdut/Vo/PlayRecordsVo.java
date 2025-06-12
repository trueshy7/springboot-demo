package cn.edu.cdut.Vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlayRecordsVo {
    private Integer playId;
    private String username;
    private String title;
    private LocalDateTime startTime;
}
