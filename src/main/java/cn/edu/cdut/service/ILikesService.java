package cn.edu.cdut.service;

import cn.edu.cdut.Dto.LikesQueryDTO;
import cn.edu.cdut.Vo.LikesVo;

import java.util.List;

public interface ILikesService {
    List<LikesVo> getLikesList(LikesQueryDTO likesQueryDTO);

    boolean deleteLikesById(int id);
}
