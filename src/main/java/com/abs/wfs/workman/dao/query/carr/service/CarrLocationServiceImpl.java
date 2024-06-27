package com.abs.wfs.workman.dao.query.carr.service;

import com.abs.wfs.workman.dao.query.carr.mapper.CarrMapper;
import com.abs.wfs.workman.dao.query.carr.vo.CarrLocationDto;
import com.abs.wfs.workman.dao.query.carr.vo.CarrMcsLocationDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CarrLocationServiceImpl {

    @Autowired
    CarrMapper carrMapper;


    public CarrLocationDto carrLocationQuery(CarrLocationDto carrLocationDto){
        return this.carrMapper.carrLocationQuery(carrLocationDto);
    }

    public CarrMcsLocationDto carrMcsLocationQuery(CarrMcsLocationDto carrMcsLocationDto){
        return this.carrMapper.carrMcsLocationQuery(carrMcsLocationDto);
    }

}
