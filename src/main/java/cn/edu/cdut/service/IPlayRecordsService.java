package cn.edu.cdut.service;

import cn.edu.cdut.Dto.PlayRecordsQueryDTO;
import cn.edu.cdut.Vo.PlayRecordsVo;
import cn.edu.cdut.pojo.PlayRecords;

import java.util.List;

public interface IPlayRecordsService {
    List<PlayRecordsVo> getPlayRecords(PlayRecordsQueryDTO playRecordsQueryDTO);

    boolean deletePlayRecordsById(int id);

    boolean addPlayRecords(PlayRecords playRecords);

    boolean updatePlayRecords(PlayRecords playRecords);
}
