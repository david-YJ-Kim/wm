package com.abs.wfs.workman.dao.query.eqp.mapper;

import com.abs.wfs.workman.dao.domain.tnPort.model.TnPosPort;
import com.abs.wfs.workman.dao.query.eqp.vo.TnPosEqpDto;
import com.abs.wfs.workman.dao.query.eqp.vo.TnPosPortDto;
import com.abs.wfs.workman.dao.query.tool.vo.QueryEqpVo;
import com.abs.wfs.workman.dao.query.tool.vo.QueryPortVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface EqpMapper {

    int updateTnPosPort(TnPosPortDto tnPosPortDto);
    int updateTnPosPort(TnPosPort tnPosPort);

    int createThPosPort(String objId);

    int updateTnPosEqp(TnPosEqpDto tnPosEqpDto);

    int createThPosEqp(String objId);

    Map<String, String> selectEqp(Map<String, String> map);
    Map<String, String> selectPort(Map<String, String> map);

    QueryEqpVo selectQueryEqpVO(Map<String, String> map);
    QueryPortVo selectQueryPortVO(Map<String, String> map);

}
