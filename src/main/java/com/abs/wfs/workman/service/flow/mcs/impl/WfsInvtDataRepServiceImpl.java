package com.abs.wfs.workman.service.flow.mcs.impl;

import com.abs.wfs.workman.service.flow.mcs.WfsInvtDataRep;
import com.abs.wfs.workman.spec.common.ApFlowProcessVo;
import com.abs.wfs.workman.spec.in.mcs.WfsInvtDataRepIvo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class WfsInvtDataRepServiceImpl implements WfsInvtDataRep {
    @Override
    public ApFlowProcessVo initialize(String cid, String trackingKey, String scenarioType) {
        return null;
    }

    @Override
    public ApFlowProcessVo execute(ApFlowProcessVo apFlowProcessVo, WfsInvtDataRepIvo wfsInvtDataRepIvo) throws Exception {
        return null;
    }
}
