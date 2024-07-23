package com.abs.wfs.workman.dao.query.wipLotProdMat.mapper;

import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Optional;

@Mapper
public interface WipLotProdMatMapper {

    /**
     * WIP, Lot, PRODUCED MATERIAL 조인하는 쿼리
     * @param wipLotProdMatDto
     * @return
     */
    Optional<List<WipLotProdMatDto>> queryLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto);

    /**
     * WIP Lot, Produced matarial을 조인하여 패널 정보를 조회
     * @param wipLotProdMatDto
     * @return
     */
    Optional<WipLotProdMatDto> queryPanelLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto);
}
