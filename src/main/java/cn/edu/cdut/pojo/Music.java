package cn.edu.cdut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;


@Data
@TableName("music")
public class Music {
    @TableId(type = IdType.AUTO)//TableId表示主键，括号内的参数表示自增长
    private Integer music_id;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private Integer duration;
    private LocalDate release_date;
    private String cover_url;
    private String file_url;
    private String upload_time;
}
