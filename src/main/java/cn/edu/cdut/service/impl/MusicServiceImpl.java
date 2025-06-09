package cn.edu.cdut.service.impl;

import cn.edu.cdut.mapper.MusicMapper;
import cn.edu.cdut.pojo.Music;
import cn.edu.cdut.service.IMusicService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MusicServiceImpl extends ServiceImpl<MusicMapper, Music> implements IMusicService {

    @Override
    public List<Music> getMusicList(Music music) {
        Map<String, Object> map = new HashMap<>();
        if (music.getAlbum() != "" && music.getAlbum() != null) {
            map.put("album", music.getAlbum());
        }
        if (music.getArtist() != "" && music.getArtist() != null) {
            map.put("artist", music.getArtist());
        }
        if (music.getTitle() != "" && music.getTitle() != null) {
            map.put("title", music.getTitle());
        }
        if (music.getGenre() != "" && music.getGenre() != null) {
            map.put("genre", music.getGenre());
        }
        List<Music> musics = baseMapper.selectByMap(map);
        return musics;
    }

    @Override
    public boolean deleteMusicById(int id) {
        Music music = baseMapper.selectById(id);
        if (music == null) {
            throw new NullPointerException("歌曲不存在");
        }
        baseMapper.deleteById(id);
        return true;
    }

    @Override
    public boolean addMusic(Music music) {
        if (music.getTitle() == "" || music.getTitle() == null) {
            throw new RuntimeException("请添加歌名");
        }
        if (music.getGenre() == "" || music.getGenre() == null) {
            throw new RuntimeException("请添加歌曲种类");
        }
        if (music.getArtist() == "" || music.getArtist() == null) {
            throw new RuntimeException("请添加歌曲作者");
        }
        HashMap<String, Object> map = new HashMap<>();
        if (music.getFile_url() != "" && music.getFile_url() != null) {
            map.put("file_url", music.getFile_url());
        }
        if(music.getAlbum()!="" && music.getAlbum()!=null){
            map.put("album", music.getAlbum());
        }
        if(music.getArtist()!="" && music.getArtist()!=null){
            map.put("artist", music.getArtist());
        }
        if(music.getTitle()!="" && music.getTitle()!=null){
            map.put("title", music.getTitle());
        }
        if(music.getGenre()!="" && music.getGenre()!=null){
            map.put("genre", music.getGenre());
        }
        List<Music> musics = baseMapper.selectByMap(map);
        if (musics.size() > 0) {
            throw new RuntimeException("该歌曲已经上传过了");
        }
        baseMapper.insert(music);
        return true;
    }

    @Override
    public boolean updateMusic(Music music) {
        if (music.getMusic_id() == null) {
            throw new RuntimeException("该歌曲不存在");
        }
        baseMapper.updateById(music);
        return true;
    }

}
