package com.abs.wfs.workman.service.flow.oi.impl;


import com.abs.wfs.workman.service.flow.oi.WfsOiCarrDestChgReq;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.oia.WfsOiCarrDestChgReqIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsOiCarrDestChgReqServiceImpl implements WfsOiCarrDestChgReq {
    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsOiCarrDestChgReqIvo wfsOiCarrDestChgReqIvo) throws Exception {
        return null;
    }

    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType, String tid) {
        return null;
    }
}
