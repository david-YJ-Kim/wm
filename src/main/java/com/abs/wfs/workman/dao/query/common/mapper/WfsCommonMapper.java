package com.abs.wfs.workman.dao.query.common.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface WfsCommonMapper {

    // ID Generator
    String getID(String name);


}
