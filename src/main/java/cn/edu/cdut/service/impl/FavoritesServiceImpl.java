package cn.edu.cdut.service.impl;

import cn.edu.cdut.Dto.FavoritesQueryDTO;
import cn.edu.cdut.Vo.FavoritesVo;
import cn.edu.cdut.mapper.FavoritesMapper;
import cn.edu.cdut.mapper.MusicMapper;
import cn.edu.cdut.mapper.UserMapper;
import cn.edu.cdut.pojo.Favorites;
import cn.edu.cdut.pojo.Music;
import cn.edu.cdut.pojo.User;
import cn.edu.cdut.service.IFavoritesService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MusicMapper musicMapper;

    @Override
    public List<FavoritesVo> getFavoritesList(FavoritesQueryDTO favoritesQueryDTO) {
        //用户名，歌曲名，起止时间，结束时间，
        //像根据用户名找到用户ID
        User user = new User();
        user.setUsername(favoritesQueryDTO.getUsername());
        HashMap<String, Object> map = new HashMap<>();
        if (user.getUsername() != null && !user.getUsername().equals("")) map.put("username", user.getUsername());
        List<User> users = userMapper.selectByMap(map);//合法users用户
        Integer userId = null;
        //userId可能有多个
        Music music = new Music();
        music.setTitle(favoritesQueryDTO.getTitle());
        map.clear();
        if (music.getTitle() != null && !music.getTitle().equals("")) map.put("title", music.getTitle());
        List<Music> musics = musicMapper.selectByMap(map);//合法歌曲
        Integer musicId = null;
        //musicId可能有多个
        LocalDateTime starttime = favoritesQueryDTO.getStarttime();
        LocalDateTime endtime = favoritesQueryDTO.getEndtime();
        if (starttime == null) {
            starttime = LocalDateTime.MIN;
        }

        if (endtime == null) {
            endtime = LocalDateTime.MAX;
        }
        //在playrecords表里面查询
        //遍历查询
        List<FavoritesVo> favoritesVos = new ArrayList<>();
        for (User user1 : users) {
            map.clear();
            for (Music music1 : musics) {
                userId = user1.getUserId();
                musicId = music1.getMusicId();
                if (userId != null) map.put("user_id", userId);
                if (musicId != null) map.put("music_id", musicId);
                List<Favorites> favorites = baseMapper.selectByMap(map);

                for (Favorites favorite : favorites) {
                    LocalDateTime favoriteTime = favorite.getFavoriteTime();
                    if (starttime.isBefore(favoriteTime) && endtime.isAfter(favoriteTime)) {
                        FavoritesVo favoritesVo = new FavoritesVo();
                        Integer playId = favorite.getFavoriteId();
                        //更具键值找用户名
                        user = userMapper.selectById(favorite.getUserId());
                        //更具键值找歌名
                        music = musicMapper.selectById(favorite.getMusicId());
                        if (user1 != null && !user1.getUsername().equals("")) {
                            favoritesVo.setUsername(user1.getUsername());
                        }
                        if (music1 != null && !music1.getTitle().equals("")) {
                            favoritesVo.setTitle(music1.getTitle());
                        }
                        favoritesVo.setFavoriteId(playId);
                        favoritesVo.setFavoriteTime(favoriteTime);
                        favoritesVos.add(favoritesVo);
                    }
                }
            }
        }
        return favoritesVos;
    }

    @Override
    public boolean deleteFavoritesById(int id) {
        int deleted = baseMapper.deleteById(id);
        if (deleted > 0) {
            return true;
        }
        return false;
    }
}
