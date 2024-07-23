package com.abs.wfs.workman.dao.query.wipLotProdMat.service;

import com.abs.wfs.workman.dao.query.wipLotProdMat.mapper.WipLotProdMatMapper;
import com.abs.wfs.workman.dao.query.wipLotProdMat.vo.WipLotProdMatDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Optional<WipLotProdMatDto> queryLotIdWithCarr(WipLotProdMatDto wipLotProdMatDto){
        return this.wipLotProdMatMapper.queryLotIdWithCarr(wipLotProdMatDto);
    }
}
