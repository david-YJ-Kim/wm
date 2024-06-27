package com.abs.wfs.workman.dao.query.carr.mapper;

import com.abs.wfs.workman.dao.query.carr.vo.CarrLocationDto;
import com.abs.wfs.workman.dao.query.carr.vo.CarrMcsLocationDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CarrMapper {

    CarrLocationDto carrLocationQuery(CarrLocationDto carrLocationDto);

    CarrMcsLocationDto carrMcsLocationQuery(CarrMcsLocationDto carrMcsLocationDto);


}
