package com.abs.wfs.workman.dao.query.tool.mapper;

import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ToolQueryMapper {


    QueryEqpVo queryEqpCondition (QueryEqpVo vo);

    QueryPortVo queryPortCondition (QueryPortVo vo);
}
