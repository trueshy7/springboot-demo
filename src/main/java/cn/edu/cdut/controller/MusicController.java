package cn.edu.cdut.controller;

import cn.edu.cdut.pojo.Music;
import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.service.IMusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/music")
@CrossOrigin
public class MusicController {

    @Autowired
    private IMusicService musicService;

    @PostMapping("/list")
    public Result getMusicList(@RequestBody Music music) {
        return Result.success(musicService.getMusicList(music));
    }

    @DeleteMapping("/delete")
    public Result deleteUser(@RequestParam("id") int id) {
        boolean deleted = musicService.deleteMusicById(id);
        if (deleted) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败，歌曲不存在");
        }
    }

    @PostMapping("/add")
    public Result addMusic(@RequestBody Music music) {
        boolean added = musicService.addMusic(music);
        if (added) {
            return Result.success(music);
        } else return Result.error("添加失败");
    }

    @PostMapping("/update")
    public Result updateMusic(@RequestBody Music music) {
        boolean updated = musicService.updateMusic(music);
        if (updated) {
            return Result.success(music);
        } else return Result.error("更新失败");
    }
}
