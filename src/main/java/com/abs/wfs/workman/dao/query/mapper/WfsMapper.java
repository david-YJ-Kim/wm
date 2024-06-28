package com.abs.wfs.workman.dao.query.mapper;


import com.abs.wfs.workman.dao.query.common.vo.StateRuleInfo;
import com.abs.wfs.workman.dao.query.model.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;


@Mapper
public interface WfsMapper {
	
	// ID Generator
	String getID(String name);
	
	List<TnPosProducedMaterial> selectTnPosProducedMaterialByLotId(String lotId);
	
	int updateTnPosCarrierMoveStatCd(TnPosCarrier tnPosCarrier);
	
	int insertThPosCarrier(String objId);
	
	List<StateRuleInfo> selectWnStateRuleInfo(StateRuleInfo stateRuleInfo);
	
	Map<String, String> selectLot(Map<String, String> map);
	
	QueryLotVO selectQueryLotVO(Map<String, String> map);
	
	Map<String, String> selectCarrier(Map<String, String> map);
	
	int insertWnMtrlUsageInfo(WnMtrlUsageInfo wnMtrlUsageInfo);
	
	int insertWhMtrlUsageInfo(String objId);
	
	int insertWhErrorInfo(WhErrorInfo wnErrorInfo);
	
	List<Map<String,String>> selectBatchLotList(Map<String, String> map);
	
	Map<String,String> selectEcoLotInfo(Map<String, String> map);
	
	List<Map<String,String>> selectProdMtrlList(Map<String, String> map);
	
	List<Map<String,String>> selectTargetGlassList(Map<String, String> map);
	
}
