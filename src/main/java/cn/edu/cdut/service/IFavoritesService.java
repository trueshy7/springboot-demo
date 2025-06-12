package cn.edu.cdut.service;

import cn.edu.cdut.Dto.FavoritesQueryDTO;
import cn.edu.cdut.Vo.FavoritesVo;

import java.util.List;

public interface IFavoritesService {
    List<FavoritesVo> getFavoritesList(FavoritesQueryDTO favoritesQueryDTO);

    boolean deleteFavoritesById(int id);
}
