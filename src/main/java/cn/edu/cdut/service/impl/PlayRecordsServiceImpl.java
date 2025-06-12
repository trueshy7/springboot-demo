package cn.edu.cdut.service.impl;

import cn.edu.cdut.Dto.PlayRecordsQueryDTO;
import cn.edu.cdut.Vo.PlayRecordsVo;
import cn.edu.cdut.mapper.MusicMapper;
import cn.edu.cdut.mapper.PlayRecordsMapper;
import cn.edu.cdut.mapper.UserMapper;
import cn.edu.cdut.pojo.Music;
import cn.edu.cdut.pojo.PlayRecords;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IPlayRecordsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class PlayRecordsServiceImpl extends ServiceImpl<PlayRecordsMapper, PlayRecords> implements IPlayRecordsService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public List<PlayRecordsVo> getPlayRecords(PlayRecordsQueryDTO playRecordsQueryDTO) {
        //用户名，歌曲名，起止时间，结束时间，
        //像根据用户名找到用户ID
        User user = new User();
        user.setUsername(playRecordsQueryDTO.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        if (user.getUsername() != null && !user.getUsername().equals("")) map.put("username", user.getUsername());
        List<User> users = userMapper.selectByMap(map);//合法users用户
        Integer userId = null;
        //userId可能有多个
        Music music = new Music();
        music.setTitle(playRecordsQueryDTO.getTitle());
        map.clear();
        if(music.getTitle()!=null&&!music.getTitle().equals("")) map.put("title", music.getTitle());
        List<Music> musics = musicMapper.selectByMap(map);//合法歌曲
        Integer musicId = null;
        //musicId可能有多个
        LocalDateTime starttime = playRecordsQueryDTO.getStarttime();
        LocalDateTime endtime = playRecordsQueryDTO.getEndtime();
        if (starttime == null) {
            starttime = LocalDateTime.MIN;
        }

        if (endtime == null) {
            endtime = LocalDateTime.MAX;
        }
        //在playrecords表里面查询
        //遍历查询
        List<PlayRecordsVo> playRecordsVos = new ArrayList<>();
        for (User user1 : users) {
            map.clear();
            for (Music music1 : musics) {
                userId = user1.getUserId();
                musicId = music1.getMusicId();
                if (userId != null) map.put("user_id", userId);
                if (musicId != null) map.put("music_id", musicId);
                List<PlayRecords> playrecords = baseMapper.selectByMap(map);

                for (PlayRecords playRecords : playrecords) {
                    LocalDateTime playTime = playRecords.getPlayTime();
                    if (starttime.isBefore(playTime) && endtime.isAfter(playTime)) {
                        PlayRecordsVo playRecordsVo = new PlayRecordsVo();
                        Integer playId = playRecords.getPlayId();
                        //更具键值找用户名
                        user = userMapper.selectById(playRecords.getUserId());
                        //更具键值找歌名
                        music = musicMapper.selectById(playRecords.getMusicId());
                        if (user1 != null && !user1.getUsername().equals("")) {
                            playRecordsVo.setUsername(user1.getUsername());
                        }
                        if (music1 != null && !music1.getTitle().equals("")) {
                            playRecordsVo.setTitle(music1.getTitle());
                        }
                        playRecordsVo.setPlayId(playId);
                        playRecordsVo.setStartTime(playTime);
                        playRecordsVos.add(playRecordsVo);
                    }
                }
            }
        }
        return playRecordsVos;
    }

    @Override
    public boolean deletePlayRecordsById(int id) {
        int deleted = baseMapper.deleteById(id);
        if (deleted > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean addPlayRecords(PlayRecords playRecords) {
        return false;
    }

    @Override
    public boolean updatePlayRecords(PlayRecords playRecords) {
        return false;
    }
}
