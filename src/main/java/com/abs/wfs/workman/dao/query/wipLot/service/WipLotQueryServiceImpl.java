package com.abs.wfs.workman.dao.query.wipLot.service;

import com.abs.wfs.workman.dao.query.wipLot.mapper.WipLotMapper;
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


    public Optional<WipLotDto> selectWipLotInfo(WipLotDto requestDto){

        return wipLotMapper.selectWipLotInfo(requestDto);
    }
}
