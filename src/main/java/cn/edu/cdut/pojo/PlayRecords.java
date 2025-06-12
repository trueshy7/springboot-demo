package cn.edu.cdut.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@TableName("play_records")//告诉mybatis-plus该类与哪张表关联
@ToString
public class PlayRecords {
    @TableId(type = IdType.AUTO)
    private Integer playId;
    private Integer userId;
    private Integer musicId;
    private LocalDateTime playTime;
}
