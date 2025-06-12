package cn.edu.cdut.controller;

import cn.edu.cdut.Dto.LikesQueryDTO;
import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.service.ILikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
@CrossOrigin
public class LikesController {
    @Autowired
    private ILikesService likesService;


    @PostMapping("/list")
    public Result getPlayRecordsList(@RequestBody LikesQueryDTO likesQueryDTO) {
        return Result.success(likesService.getLikesList(likesQueryDTO));
    }

    @DeleteMapping("/delete")
    public Result deleteLikes(@RequestParam("id") int id) {
        boolean deleted = likesService.deleteLikesById(id);
        if (deleted) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败,该点赞不存在");
        }
    }
}
