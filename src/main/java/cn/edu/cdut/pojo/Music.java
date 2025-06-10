package cn.edu.cdut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;


@Data
@TableName("music")
@ToString
public class Music {
    @TableId(type = IdType.AUTO,value="music_id")//TableId表示主键，括号内的参数表示自增长
    private Integer musicId;
    private String title;
    private String artist;
    private String album;
    private String genre;
    private Integer duration;
    private LocalDate releaseDate;
    private String coverUrl;
    private String fileUrl;
    private String uploadTime;
}
