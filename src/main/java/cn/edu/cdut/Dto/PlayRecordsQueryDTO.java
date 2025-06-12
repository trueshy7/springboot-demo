package cn.edu.cdut.Dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PlayRecordsQueryDTO {
    private String username;
    private String title;//musictitle
    private LocalDateTime starttime;
    private LocalDateTime endtime;
}
