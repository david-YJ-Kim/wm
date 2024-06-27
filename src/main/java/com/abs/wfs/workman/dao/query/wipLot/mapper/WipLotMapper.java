package com.abs.wfs.workman.dao.query.wipLot.mapper;

import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WipLotMapper {

    Optional<WipLotDto> selectWipLotInfo(WipLotDto requestDto);
}
