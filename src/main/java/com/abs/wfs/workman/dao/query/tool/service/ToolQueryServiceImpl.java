package com.abs.wfs.workman.dao.query.tool.service;

import com.abs.wfs.workman.dao.query.tool.mapper.ToolQueryMapper;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ToolQueryServiceImpl implements ToolQueryService {

    @Autowired
    ToolQueryMapper toolQueryMapper;


    public QueryEqpVo queryEqpCondition(QueryEqpVo vo) {

        return toolQueryMapper.queryEqpCondition(vo);
    }

    public QueryPortVo queryPortCondition(QueryPortVo vo){
        return toolQueryMapper.queryPortCondition(vo);
    }
}
