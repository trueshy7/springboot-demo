package cn.edu.cdut.service;

import cn.edu.cdut.pojo.Music;

import java.util.List;

public interface IMusicService {
    List<Music> getMusicList(Music music);

    boolean updateMusic(Music music);

    boolean deleteMusicById(int id);

    boolean addMusic(Music music);

}
