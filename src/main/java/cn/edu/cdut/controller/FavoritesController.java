package cn.edu.cdut.controller;

import cn.edu.cdut.Dto.FavoritesQueryDTO;
import cn.edu.cdut.pojo.Result;
import cn.edu.cdut.service.IFavoritesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favorites")
@CrossOrigin
public class FavoritesController {
    @Autowired
    private IFavoritesService favoritesService;

    @PostMapping("/list")
    public Result getFavoritesList(@RequestBody FavoritesQueryDTO favoritesQueryDTO) {
        return Result.success(favoritesService.getFavoritesList(favoritesQueryDTO));
    }

    @DeleteMapping("/delete")
    public Result deleteFavorites(@RequestParam("id") int id) {
        boolean deleted = favoritesService.deleteFavoritesById(id);
        if (deleted) {
            return Result.success("删除成功");
        } else {
            return Result.error("删除失败,歌曲不存在");
        }
    }

}
