package com.abs.wfs.workman.dao.query.common.mapper;

import com.abs.wfs.workman.dao.domain.stateRuleInfo.model.WnStateRuleInfo;
import com.abs.wfs.workman.dao.query.common.vo.TnPosCarrier;
import com.abs.wfs.workman.dao.query.common.vo.TnPosProducedMaterial;
import com.abs.wfs.workman.dao.query.common.vo.WhErrorInfo;
import com.abs.wfs.workman.dao.query.common.vo.WnMtrlUsageInfo;
import com.abs.wfs.workman.dao.query.lot.vo.QueryLotVo;
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

    List<WnStateRuleInfo> selectWnStateRuleInfo(WnStateRuleInfo stateRuleInfo);

    Map<String, String> selectLot(Map<String, String> map);

    QueryLotVo selectQueryLotVO(Map<String, String> map);

    Map<String, String> selectCarrier(Map<String, String> map);

    int insertWnMtrlUsageInfo(WnMtrlUsageInfo wnMtrlUsageInfo);

    int insertWhErrorInfo(WhErrorInfo wnErrorInfo);

    List<Map<String,String>> selectBatchLotList(Map<String, String> map);

}
