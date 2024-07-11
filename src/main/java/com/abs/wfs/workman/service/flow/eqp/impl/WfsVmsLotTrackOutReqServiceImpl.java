package com.abs.wfs.workman.service.flow.eqp.impl;


import com.abs.wfs.workman.service.flow.eqp.WfsVmsLotTrackOutReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.eqp.WfsVmsLotTrackOutReqIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsVmsLotTrackOutReqServiceImpl implements WfsVmsLotTrackOutReq {
    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsVmsLotTrackOutReqIvo wfsVmsLotTrackOutReqIvo) throws Exception {
        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        return null;
    }
}
