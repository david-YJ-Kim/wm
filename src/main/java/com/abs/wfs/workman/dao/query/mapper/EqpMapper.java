package com.abs.wfs.workman.dao.query.mapper;

import com.abs.wfs.workman.dao.query.model.QueryEqpVO;
import com.abs.wfs.workman.dao.query.model.QueryPortVO;
import com.abs.wfs.workman.dao.query.model.TnPosEqp;
import com.abs.wfs.workman.dao.query.model.TnPosPort;
import org.apache.ibatis.annotations.Mapper;

import java.util.Map;


@Mapper
public interface EqpMapper {
	int updateTnPosPort(TnPosPort tnPosPort);
	
	int createThPosPort(String objId);
	
	int updateTnPosEqp(TnPosEqp tnPosEqp);
	
	int createThPosEqp(String objId);
	
	Map<String, String> selectEqp(Map<String, String> map);	
	Map<String, String> selectPort(Map<String, String> map);
	
	QueryEqpVO selectQueryEqpVO(Map<String, String> map);
	QueryPortVO selectQueryPortVO(Map<String, String> map);
	
	
}
