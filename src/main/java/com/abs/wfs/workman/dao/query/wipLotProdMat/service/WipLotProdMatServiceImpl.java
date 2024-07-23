package com.abs.wfs.workman.dao.query.wipLotProdMat.service;

import com.abs.wfs.workman.dao.query.wipLotProdMat.mapper.WipLotProdMatMapper;
import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WipLotProdMatServiceImpl {

    @Autowired
    WipLotProdMatMapper wipLotProdMatMapper;

    /**
     * WIP, Lot, PRODUCED MATERIAL 조인하는 쿼리
     * @param wipLotProdMatDto
     * @return
     */
    public Optional<List<WipLotProdMatDto>> queryLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto){
        return this.wipLotProdMatMapper.queryLotIdWithCarr(wipLotProdMatDto);
    }


    /**
     * WIP Lot, Produced matarial을 조인하여 패널 정보를 조회
     * @param wipLotProdMatDto
     * @return
     */
    public Optional<WipLotProdMatDto> queryPanelLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto){
        return this.wipLotProdMatMapper.queryPanelLotIdWithCarr(wipLotProdMatDto);
    }


}
