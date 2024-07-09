package com.abs.wfs.workman.dao.query.wipLot.service;

import com.abs.wfs.workman.dao.query.wipLot.mapper.WipLotMapper;
import com.abs.wfs.workman.dao.query.wipLot.vo.SelectCarrLocQueryReqVo;
import com.abs.wfs.workman.dao.query.wipLot.vo.WipLotDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@Slf4j
public class WipLotQueryServiceImpl implements WipLotQueryService{

    @Autowired
    WipLotMapper wipLotMapper;


    /**
     * Carr Lot으로 Wip과 Lot 조인하여 조회, Outer 조인
     * @param vo
     * @return
     */
    public Optional<SelectCarrLocQueryReqVo> selectCarrLocQuery(SelectCarrLocQueryReqVo vo){
        return this.wipLotMapper.selectCarrLocQuery(vo);
    }

    public Optional<WipLotDto> selectWipLotInfo(WipLotDto requestDto){

        return wipLotMapper.selectWipLotInfo(requestDto);
    }
}
