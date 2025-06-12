package cn.edu.cdut.controller;

import cn.edu.cdut.Dto.PlayRecordsQueryDTO;
import cn.edu.cdut.pojo.PlayRecords;
import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.service.IPlayRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/playrecords")
@CrossOrigin
public class PlayRecordsController {
    @Autowired
    private IPlayRecordsService playRecordsService;


    @PostMapping("/list")
    public Result getPlayRecordsList(@RequestBody PlayRecordsQueryDTO playRecordsQueryDTO) {
        return Result.success(playRecordsService.getPlayRecords(playRecordsQueryDTO));
    }

    @DeleteMapping("/delete")
    public Result deletePlayRecords(@RequestParam("id") int id) {
        boolean deleted = playRecordsService.deletePlayRecordsById(id);
        if (deleted) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败,歌曲不存在");
        }
    }

    @PostMapping("/add")
    public Result addPlayRecords(@RequestBody PlayRecords playRecords) {
        if (!playRecordsService.addPlayRecords(playRecords)) {
            return Result.error("添加失败");
        } else {
            return Result.success("添加成功");
        }
    }

    @PostMapping("/update")
    public Result updateUser(@RequestBody PlayRecords playRecords) {
        if (!playRecordsService.updatePlayRecords(playRecords)) {
            return Result.error("更新失败");
        }
        return Result.success("更新成功");
    }
}
