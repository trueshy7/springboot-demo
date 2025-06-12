package cn.edu.cdut.service.impl;

import cn.edu.cdut.Dto.LikesQueryDTO;
import cn.edu.cdut.Vo.LikesVo;
import cn.edu.cdut.mapper.LikesMapper;
import cn.edu.cdut.mapper.MusicMapper;
import cn.edu.cdut.mapper.UserMapper;
import cn.edu.cdut.pojo.Likes;
import cn.edu.cdut.pojo.Music;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.ILikesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class LikesServiceImpl extends ServiceImpl<LikesMapper, Likes> implements ILikesService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public List<LikesVo> getLikesList(LikesQueryDTO likesQueryDTO) {
        User user = new User();
        user.setUsername(likesQueryDTO.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        if (user.getUsername() != null && !user.getUsername().equals("")) map.put("username", user.getUsername());
        List<User> users = userMapper.selectByMap(map);//合法users用户
        Integer userId = null;
        //userId可能有多个
        Music music = new Music();
        music.setTitle(likesQueryDTO.getTitle());
        map.clear();
        if (music.getTitle() != null && !music.getTitle().equals("")) map.put("title", music.getTitle());
        List<Music> musics = musicMapper.selectByMap(map);//合法歌曲
        Integer musicId = null;
        //musicId可能有多个
        LocalDateTime starttime = likesQueryDTO.getStarttime();
        LocalDateTime endtime = likesQueryDTO.getEndtime();
        if (starttime == null) {
            starttime = LocalDateTime.MIN;
        }

        if (endtime == null) {
            endtime = LocalDateTime.MAX;
        }
        //在playrecords表里面查询
        //遍历查询
        List<LikesVo> likeVos = new ArrayList<>();
        for (User user1 : users) {
            map.clear();
            for (Music music1 : musics) {
                userId = user1.getUserId();
                musicId = music1.getMusicId();
                if (userId != null) map.put("user_id", userId);
                if (musicId != null) map.put("music_id", musicId);
                List<Likes> likes = baseMapper.selectByMap(map);

                for (Likes like : likes) {
                    LocalDateTime likeTime = like.getLikeTime();
                    if (starttime.isBefore(likeTime) && endtime.isAfter(likeTime)) {
                        LikesVo likesVo = new LikesVo();
                        Integer likeId = like.getLikeId();
                        //更具键值找用户名
                        user = userMapper.selectById(like.getUserId());
                        //更具键值找歌名
                        music = musicMapper.selectById(like.getMusicId());
                        if (user1 != null && !user1.getUsername().equals("")) {
                            likesVo.setUsername(user1.getUsername());
                        }
                        if (music1 != null && !music1.getTitle().equals("")) {
                            likesVo.setTitle(music1.getTitle());
                        }
                        likesVo.setLikeId(likeId);
                        likesVo.setLikeTime(likeTime);
                        likeVos.add(likesVo);
                    }
                }
            }
        }
        return likeVos;
    }

    @Override
    public boolean deleteLikesById(int id) {
        int deleted = baseMapper.deleteById(id);
        if (deleted > 0) {
            return true;
        }
        return false;
    }
}
