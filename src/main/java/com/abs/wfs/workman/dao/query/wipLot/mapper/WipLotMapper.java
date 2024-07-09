package com.abs.wfs.workman.dao.query.wipLot.mapper;

import com.abs.wfs.workman.dao.query.wipLot.vo.SelectCarrLocQueryReqVo;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WipLotMapper {

    Optional<WipLotDto> selectWipLotInfo(WipLotDto requestDto);

    /**
     * Carr Lot으로 Wip과 Lot 조인하여 조회, Outer 조인
     * @param requestDto
     * @return
     */
    Optional<SelectCarrLocQueryReqVo> selectCarrLocQuery(SelectCarrLocQueryReqVo requestDto);
}
