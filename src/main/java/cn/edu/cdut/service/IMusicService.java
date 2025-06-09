package cn.edu.cdut.service;

import cn.edu.cdut.pojo.Music;

import java.util.List;

public interface IMusicService {
    List<Music> getMusicList(Music music);

    boolean deleteMusicById(int music_id);


    boolean updateMusic(Music music);

    boolean addMusic(Music music);

}
