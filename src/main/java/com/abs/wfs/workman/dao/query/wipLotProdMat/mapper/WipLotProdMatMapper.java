package com.abs.wfs.workman.dao.query.wipLotProdMat.mapper;

import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

@Mapper
public interface WipLotProdMatMapper {

    /**
     * WIP, Lot, PRODUCED MATERIAL 조인하는 쿼리
     * @param wipLotProdMatDto
     * @return
     */
    Optional<WipLotProdMatDto> queryLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto);
}
